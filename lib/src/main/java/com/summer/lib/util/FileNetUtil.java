package com.summer.lib.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;


/**
 * Created by ${viwmox} on 2016-05-05.
 */
public class FileNetUtil {

    private static FileNetUtil instance;

    public static FileNetUtil getInstance() {
        if (instance == null) {
            instance = new FileNetUtil();
        }
        return instance;
    }

//    /**
//     * 上传文件(支持多文件上传)
//     */
//    public void uploadfile(final Context context,String server, final ArrayList<Uri> fileUris) {
//
//
//
//
//
//        RequestParams requestParams=new RequestParams(server);
////        requestParams.addQueryStringParameter("filesize","13");
////        requestParams.addBodyParameter("filesize", "13");
//        for(int i=0;i<fileUris.size();i++){
//            File file =new File(getPath(context, fileUris.get(i)));
//            String[] str =fileUris.get(i).toString().split("/");
//            requestParams.addBodyParameter("image"+str[str.length-1],file);
//        }
//
//        requestParams.setMultipart(true);
//        x.http().post(requestParams, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                LogUtil.E("onSuccess");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LogUtil.E("onError"+ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                LogUtil.E("onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                LogUtil.E("onFinished");
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                return false;
//            }
//        });
//
//    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


//    public  String post(final Context context,String server, final Uri fileUri) throws ClientProtocolException, IOException {
//        HttpClient httpclient = new DefaultHttpClient();
//
//        HttpPost post = new HttpPost(server);
//        FileBody fileBody = new FileBody(new File(getPath(context, fileUri)));
//        StringBody stringBody = new StringBody("文件的描述");
//        MultipartEntity entity = new MultipartEntity();
//        entity.addPart("file", fileBody);
//        entity.addPart("desc", stringBody);
////        entity.addPart("quotationid",new StringBody(res.getQuotations().get(currentIndex).getQuotationid()+""));
////        entity.addPart("handleType",new StringBody("1"));
//        post.setEntity(entity);
//        HttpResponse response = httpclient.execute(post);
//        InputStream is=response.getEntity().getContent();
//        BufferedReader br=new BufferedReader(new InputStreamReader(is,"ISO-8859-1"));
//        StringBuffer sb=new StringBuffer();
//        String line="";
//        while((line=br.readLine())!=null){
//            sb.append(line);
//        }
//        is.close();
//        br.close();
//        httpclient.getConnectionManager().shutdown();
//        return sb.toString();
//
//    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 上传文件(支持多文件上传)
     */
//    public void uploadfile(final Context context,String server, final ArrayList<File> fileUris) {
//
//
//        RequestParams requestParams=new RequestParams(server);
////        requestParams.addQueryStringParameter("filesize","13");
////        requestParams.addBodyParameter("filesize", "13");
//        for(int i=0;i<fileUris.size();i++){
//            String[] str =fileUris.get(i).toString().split("/");
//            requestParams.addBodyParameter("image"+str[str.length-1],fileUris.get(i));
//        }
//
//        requestParams.setMultipart(true);
//        x.http().post(requestParams, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                LogUtil.E("onSuccess");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LogUtil.E("onError"+ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                LogUtil.E("onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                LogUtil.E("onFinished");
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                return false;
//            }
//        });
//
//    }

    /**
     * 上传文件(支持多文件上传)
     */
//    public void uploadfile(final Context context,String server, HashMap<String,String> values,final ArrayList<File> fileUris) {
//
//
//        RequestParams requestParams=new RequestParams(server);
//        for(int i=0;i<fileUris.size();i++){
//            String[] str =fileUris.get(i).toString().split("/");
//            requestParams.addBodyParameter("image"+str[str.length-1],fileUris.get(i));
//        }
//
//        Set<String> stringSet= values.keySet();
//        if(stringSet!=null){
//            Iterator<String> stringIterator = stringSet.iterator();
//            while (stringIterator.hasNext()){
//                String str =stringIterator.next();
//                requestParams.addBodyParameter(str,values.get(str));
//            }
//
//        }
//
//        requestParams.setMultipart(true);
//        x.http().post(requestParams, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                LogUtil.E("onSuccess");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LogUtil.E("onError"+ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                LogUtil.E("onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                LogUtil.E("onFinished");
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                return false;
//            }
//        });
//    }
//
//    /**
//     * 上传文件(支持多文件上传)
//     */
//    public void uploadfile(final Context context,String server, HashMap<String,String> values,final ArrayList<File> fileUris,Callback.CacheCallback<String> cacheCallback) {
//
//
//        RequestParams requestParams=new RequestParams(server);
//        for(int i=0;i<fileUris.size();i++){
//            String[] str =fileUris.get(i).toString().split("/");
//            requestParams.addBodyParameter("image"+str[str.length-1],fileUris.get(i));
//        }
//
//        Set<String> stringSet= values.keySet();
//        if(stringSet!=null){
//            Iterator<String> stringIterator = stringSet.iterator();
//            while (stringIterator.hasNext()){
//                String str =stringIterator.next();
//                requestParams.addBodyParameter(str,values.get(str));
//            }
//
//        }
//
//        requestParams.setMultipart(true);
//        x.http().post(requestParams,cacheCallback);
//    }
//
//    /**
//     * 上传文件(支持多文件上传)
//     */
//    public void uploadHead(final Context context,String server,final ArrayList<File> fileUris,Callback.CacheCallback<String> cacheCallback) {
//
//
//        RequestParams requestParams=new RequestParams(server);
//        for(int i=0;i<fileUris.size();i++){
//            String[] str =fileUris.get(i).toString().split("/");
//            requestParams.addBodyParameter("head"+str[str.length-1],fileUris.get(i));
//        }
//        x.http().post(requestParams,cacheCallback);
//    }


}
