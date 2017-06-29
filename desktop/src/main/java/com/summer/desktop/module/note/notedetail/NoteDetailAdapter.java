package com.summer.desktop.module.note.notedetail;

//by summer on 2017-05-27.

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.databean.EventBean;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.ImageNote;
import com.summer.desktop.bean.dabean.LinkNote;
import com.summer.desktop.bean.dabean.NoteDetail;
import com.summer.desktop.bean.dabean.TxtNote;
import com.summer.desktop.util.GlideApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailAdapter extends RecyclerView.Adapter implements View.OnLongClickListener, View.OnClickListener {

    Context context;

    ArrayList<NoteDetail> data;

    Gson gson = new Gson();

    OnFinishListener onFinishListener;

    GsonNoteBean bean;

    View.OnClickListener onClickListener;

    public NoteDetailAdapter(Context context, GsonNoteBean bean) {
        this.context = context;
        this.data = bean.getData();
        this.bean = bean;
    }

    @Override
    public int getItemViewType(int position) {
        switch (data.get(position).getType()) {
            case NoteDetail.IMAGE:
                return 0;
            case NoteDetail.LINK:
                return 2;
            default:
                return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                viewHolder = new ImageHolder(LayoutInflater.from(context).inflate(R.layout.item_note_image, parent, false));
                break;
            case 1:
                viewHolder = new TxtHolder(LayoutInflater.from(context).inflate(R.layout.item_note_txt, parent, false));
                break;
            case 2:
                viewHolder = new WebHolder(LayoutInflater.from(context).inflate(R.layout.item_note_webview, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                ImageHolder imageHolder = (ImageHolder) holder;
                final ImageNote imageNote = gson.fromJson(data.get(position).getData(), ImageNote.class);
                switch (bean.getType()) {
                    case GsonNoteBean.TYPE_GALLERY:
                        break;
                    default:
                        imageHolder.itemView.getLayoutParams().width = (int) ScreenUtil.w;
                        imageHolder.itemView.getLayoutParams().height = (int) (ScreenUtil.w * imageNote.getHeight() / imageNote.getWidth());
                        imageHolder.itemView.requestLayout();
                        break;
                }
                if (imageNote.getLocalSrc() != null && imageNote.getLocalSrc().startsWith("file://")) {
                    File file = new File(imageNote.getLocalSrc().substring("file://".length(), imageNote.getLocalSrc().length()));
                    if (file.exists()) {
                        GlideApp.with(context).load(imageNote.getLocalSrc()).placeholder(R.drawable.app).encodeQuality(100).into(imageHolder.imageView);
                        //com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageNote.getLocalSrc(),imageHolder.imageView);
                    } else {
                        // GlideApp.with(context).load(imageNote.getLocalSrc()).placeholder(R.drawable.app).diskCacheStrategy(DiskCacheStrategy.ALL).encodeQuality(10).into(imageHolder.imageView);
                        GlideApp.with(context).load(imageNote.getSrc()).placeholder(R.drawable.app).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageHolder.imageView);
                        saveBitmap(imageNote);
                        //com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageNote.getLocalSrc(),imageHolder.imageView);
                    }
                } else {
                    GlideApp.with(context).load(imageNote.getSrc()).placeholder(R.drawable.app).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageHolder.imageView);
                    //com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageNote.getSrc(),imageHolder.imageView);
                    saveBitmap(imageNote);
                }
                imageHolder.itemView.setTag(R.id.position, position);
                imageHolder.itemView.setTag(R.id.data, data.get(position));
                imageHolder.itemView.setTag(R.id.data1, data);
                imageHolder.itemView.setOnLongClickListener(this);
                imageHolder.itemView.setOnClickListener(this);
                break;
            case 1:
                final TxtHolder txtHolder = (TxtHolder) holder;
                final TxtNote txtNote = gson.fromJson(data.get(position).getData(), TxtNote.class);
                txtHolder.textView.setText(txtNote.getTxt());
                txtHolder.itemView.setTag(R.id.position, position);
                txtHolder.itemView.setTag(R.id.data, data.get(position));
                txtHolder.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            txtNote.setTxt(txtHolder.textView.getText().toString());
                            data.get(position).setData(gson.toJson(txtNote));
                        }
                    }
                });
                break;
            case 2:
                final WebHolder webHolder = (WebHolder) holder;
                LinkNote linkNote = gson.fromJson(data.get(position).getData(), LinkNote.class);
                webHolder.webView.getSettings().setJavaScriptEnabled(true);
                webHolder.webView.addJavascriptInterface(new MyJavaScriptInterface() {
                    @Override
                    public void showHTML(String html) {
                        super.showHTML(html);
                        webHolder.textView.setText(Html.fromHtml(html));
                    }
                }, "HtmlViewer");
                webHolder.webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        webHolder.webView.loadUrl("javascript:window.HtmlViewer.showHTML" +
                                "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                    }
                });
                webHolder.webView.loadUrl("http://www.baidu.com");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean onLongClick(View v) {
        EventBean eventBean = new EventBean() {
            @Override
            public void onFinish() {
                notifyDataSetChanged();
            }
        };
        if (onFinishListener != null) {
            eventBean.msg = data;
            eventBean.msg2 = v.getTag(R.id.position);
            onFinishListener.onFinish(eventBean);
        }
        return true;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void saveBitmap(final ImageNote imageNote) {
        LogUtil.E(imageNote.toString());
        GlideApp.with(context).asBitmap().load(imageNote.getSrc()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
                new AsyncTask<String, String, ImageNote>() {
                    @Override
                    protected ImageNote doInBackground(String... params) {
                        String s = imageNote.getLocalSrc().substring("file://".length(), imageNote.getLocalSrc().length());
                        File file = new File(s);
                        String[] sss = s.split("\\/");
                        String end = sss[sss.length - 1];
                        File file2 = new File(s.replace(end, ""));
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        OutputStream os = null;
                        try {
                            os = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            LogUtil.E(e);
                            e.printStackTrace();
                        }
                        LogUtil.E(resource == null);
                        LogUtil.E(os == null);
//                        String[] s1 = name.split("\\.");
//                        String s2= s1[s1.length-1];
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, os);
                        return imageNote;
                    }

                    @Override
                    protected void onPostExecute(ImageNote imageNote) {
                        super.onPostExecute(imageNote);
                        LogUtil.E(imageNote.toString());
                    }
                }.execute();
            }
        });
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TxtHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt)
        EditText textView;

        public TxtHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class WebHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.webview)
        WebView webView;

        @BindView(R.id.textView)
        TextView textView;

        public WebHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyJavaScriptInterface {


        String html;

        @JavascriptInterface
        public void showHTML(String html) {
            this.html = html;
            LogUtil.E(html);
        }

    }
}
