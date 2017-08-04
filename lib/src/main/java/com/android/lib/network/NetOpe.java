package com.android.lib.network;

//by summer on 2017-08-01.

import android.content.Context;

import com.android.lib.base.ope.BaseOpe;
import com.android.lib.bean.FilesBean;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetOpe implements BaseOpe {


    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_ERROR = 1;
    public static final int TYPE_CANCELLED = 2;
    public static String NET_DOMAIN = "";
    public static String NET_URL = "";
    public static String COOKIE = "";
    private static NetOpe instance;


    private NetOpe() {
    }

    public static NetOpe getInstance() {
        if (instance == null) {
            instance = new NetOpe();
        }
        return instance;
    }

    public <T extends BaseResBean> void doHttpRequset(Context context, String model, final BaseReqBean reqBean, final onNetProcess<T> onNetProcess, final Class<T> t) {
        final String jsonstr = GsonUtil.getInstance().toJson(reqBean);
        LogUtil.E("url:" + NET_URL + model + "\n" + jsonstr);
        onNetProcess.onStart(NET_URL + model, jsonstr);
        RequestParams requestParams = new RequestParams(NET_URL + model);
        requestParams.setUseCookie(true);
        requestParams.setHeader("Cookie", COOKIE);
        requestParams.setConnectTimeout(30 * 1000);
        Map<String, String> map = GsonUtil.getInstance().fromJson(jsonstr, new TypeToken<Map<String, String>>() {
        }.getType());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }
        T tt = null;
        try {
            tt = t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        final T finalTt = tt;
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtil.E(response);
                onNetProcess.onResult(GsonUtil.getInstance().fromJson(response, t));
                onNetProcess.onEnd(TYPE_SUCCESS, response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.E("" + ex.getMessage());
                finalTt.setErrorCode(-1);
                finalTt.setErrorMessage("" + ex.getMessage());
                finalTt.setException(true);
                onNetProcess.onResult(finalTt);
                onNetProcess.onEnd(TYPE_ERROR, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                onNetProcess.onEnd(TYPE_CANCELLED, cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void doHttpRequsetWithFile(Context context, String model, final int position, FilesBean data, final onNetProcess<String> onNetProcess) {
        onNetProcess.onStart(NET_URL + model, "");
        RequestParams requestParams = new RequestParams(NET_URL + model);


        List<KeyValue> list = new ArrayList<>();
        for (int i = 0; i < data.getData().size(); i++) {
            list.add(new KeyValue("file", data.getData().get(i).getFile()));
            //requestParams.addBodyParameter("file"+i, data.getData().get(i).getFile(),null);
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        requestParams.setRequestBody(body);
        requestParams.setMultipart(true);


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtil.E(response);
                onNetProcess.onResult(position + "");
                onNetProcess.onEnd(TYPE_SUCCESS, response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onNetProcess.onResult("-1");
                onNetProcess.onEnd(TYPE_ERROR, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                onNetProcess.onEnd(TYPE_CANCELLED, cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public interface onNetProcess<T> {

        public void onStart(String url, String req);

        public void onEnd(int type, String res);

        public void onResult(T t);
    }
}
