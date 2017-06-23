package com.android.lib.network;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.lib.constant.UrlConstant;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.net.HttpCookie;
import java.util.Map;


/**
 * Created by ${viwmox} on 2016-04-26.
 */
public class NetWork {

    private static NetWork instance;

    private static Gson gson = new Gson();

    private static RequestQueue mQueue;


    private NetWork() {

    }

    public static NetWork getInstance(Context context) {
        if (instance == null) {
            instance = new NetWork();
            mQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return instance;
    }

    public void init(String url) {
        UrlConstant.URI = url;
    }

    /**
     * @param tag
     */
    public void cancle(Context tag) {
        mQueue.cancelAll(tag);
    }

//    @Deprecated
//    public void doHttpRequset(final Context context, final String model, final BaseReqBean reqBean, final OnNetWorkReqInterf reqInterf) {
//        JsonObjectRequest jsonObjectRequest = null;
//        LogUtil.E(UrlConstant.URI + model);
//        final String jsonstr = gson.toJson(reqBean);
//        LogUtil.E(jsonstr);
//        if (!reqInterf.onNetWorkReqStart(UrlConstant.URI + model, jsonstr)) {
//            BaseResBean res = new BaseResBean();
//            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
//            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
//            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//            return;
//        }
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlConstant.URI + model,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response == null) {
//                            BaseResBean res = new BaseResBean();
//                            res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
//                            res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
//                            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//                        } else {
//                            BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
//                            reqInterf.onNetWorkReqFinish(true, UrlConstant.URI + model, baseResBean);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                BaseResBean baseResBean = new BaseResBean();
//                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
//                baseResBean.setErrorMessage(error.getMessage() == null ? "" : error.getMessage());
//                reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, baseResBean);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map = (Map<String, String>) gson.fromJson(jsonstr, map.getClass());
//                return map;
//            }
//        };
//        stringRequest.setTag(context.getClass().getName());
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        stringRequest.setShouldCache(true);
//        mQueue.add(stringRequest);
//    }

//    @Deprecated
//    public void doHttpRequsetWithS(final Context context, final String model, final BaseReqBean reqBean, final OnNetWorkReqInterf reqInterf) {
//        JsonObjectRequest jsonObjectRequest = null;
//        LogUtil.E(UrlConstant.URI + model);
//        final String jsonstr = gson.toJson(reqBean);
//        LogUtil.E(jsonstr);
//        if (!reqInterf.onNetWorkReqStart(UrlConstant.URI + model, jsonstr)) {
//            BaseResBean res = new BaseResBean();
//            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
//            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
//            res.setData(jsonstr);
//            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//            return;
//        }
//        Request stringRequest = null;
//        stringRequest = new MyStringRequest(Request.Method.POST, UrlConstant.URI + model,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response == null) {
//                            BaseResBean res = new BaseResBean();
//                            res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
//                            res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
//                            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//                        } else {
//                            BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
//                            reqInterf.onNetWorkReqFinish(true, UrlConstant.URI + model, baseResBean);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                BaseResBean baseResBean = new BaseResBean();
//                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
//                baseResBean.setErrorMessage(error.getMessage() == null ? "" : error.getMessage());
//                reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, baseResBean);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String,String> map = new HashMap<String,String>();
////                map = (Map<String,String>) gson.fromJson(jsonstr, map.getClass());
//
//                Map<String, String> map = JSON.parseObject(jsonstr, new TypeReference<Map<String, String>>() {
//                });
//
//                return map;
//            }
//        };
//        stringRequest.setTag(context.getClass().getName());
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        stringRequest.setShouldCache(true);
//        mQueue.add(stringRequest);
//    }

    public void dologin(final Context context, final String model, final BaseReqBean reqBean, final OnNetWorkReqInterf reqInterf) {
        LogUtil.E(UrlConstant.URI + model);
        final String jsonstr = gson.toJson(reqBean);
        LogUtil.E(jsonstr);
        if (!reqInterf.onNetWorkReqStart(UrlConstant.URI + model, jsonstr)) {
            BaseResBean res = new BaseResBean();
            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
            res.setData(jsonstr);
            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
            return;
        }

        RequestParams requestParams = new RequestParams(UrlConstant.URI + model);
        requestParams.setUseCookie(true);
        Map<String, String> map = JSON.parseObject(jsonstr, new TypeReference<Map<String, String>>() {
        });
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                DbCookieStore dbCookieStore = DbCookieStore.INSTANCE;
                for (HttpCookie cookie : dbCookieStore.getCookies()) {
                    ValueConstant.cookieFromResponse = cookie.toString();
                    SPUtil.getInstance().saveStr(ValueConstant.cookieFromResponse, cookie.toString());
                    LogUtil.E(ValueConstant.cookieFromResponse);
                }
                if (response == null) {
                    BaseResBean res = new BaseResBean();
                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
                    reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
                } else {
                    BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
                    reqInterf.onNetWorkReqFinish(true, UrlConstant.URI + model, baseResBean);
                }
                LogUtil.E(response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
                baseResBean.setErrorMessage(ex.getMessage() == null ? "" : ex.getMessage());
                reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, baseResBean);
                LogUtil.E(ex == null ? "" : ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.E(cex);
            }

            @Override
            public void onFinished() {
                LogUtil.E("");
            }
        });
    }

    public void doHttpRequsetWithSession(final Context context, final String model, final BaseReqBean reqBean, final OnNetWorkReqInterf reqInterf) {
        LogUtil.E(UrlConstant.URI + model);
        final String jsonstr = gson.toJson(reqBean);
        LogUtil.E(jsonstr);
        if (!reqInterf.onNetWorkReqStart(UrlConstant.URI + model, jsonstr)) {
            BaseResBean res = new BaseResBean();
            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
            res.setData(jsonstr);
            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
            return;
        }

        RequestParams requestParams = new RequestParams(UrlConstant.URI + model);
        requestParams.setUseCookie(true);
        requestParams.setHeader("Cookie", SPUtil.getInstance().getStr(ValueConstant.cookieFromResponse));
        LogUtil.E(UrlConstant.URI + model + "---" + ValueConstant.cookieFromResponse);
        Map<String, String> map = JSON.parseObject(jsonstr, new TypeReference<Map<String, String>>() {
        });
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                if (response == null) {
                    BaseResBean res = new BaseResBean();
                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
                    reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
                } else {
                    BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
                    reqInterf.onNetWorkReqFinish(true, UrlConstant.URI + model, baseResBean);
                }
                LogUtil.E("gson", response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
                baseResBean.setErrorMessage(ex.getMessage() == null ? "" : ex.getMessage());
                baseResBean.setException(true);
                reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, baseResBean);
                LogUtil.E(ex == null ? "" : ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.E(cex);
            }

            @Override
            public void onFinished() {
                LogUtil.E("");
            }
        });
    }

//    @Deprecated
//    public void doHttpRequsetWithS(final Context context, final String url, final String req, final OnNetWorkReqInterf reqInterf) {
//        LogUtil.E(req);
//        Request stringRequest = null;
//        stringRequest = new MyStringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response == null) {
//                            BaseResBean res = new BaseResBean();
//                            res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
//                            res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
//                            reqInterf.onNetWorkReqFinish(false, url, res);
//                        } else {
//                            BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
//                            reqInterf.onNetWorkReqFinish(true, url, baseResBean);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                BaseResBean baseResBean = new BaseResBean();
//                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
//                baseResBean.setErrorMessage(error.getMessage() == null ? "" : error.getMessage());
//                reqInterf.onNetWorkReqFinish(false, url, baseResBean);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map = (Map<String, String>) gson.fromJson(req, map.getClass());
//                return map;
//            }
//        };
//        stringRequest.setTag(context.getClass().getName());
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        stringRequest.setShouldCache(true);
//        mQueue.add(stringRequest);
//    }
//
//    @Deprecated
//    public void doObjectHttpRequsetWithSession(final Context context, final String model, final BaseReqBean reqBean, final OnNetWorkReqInterf reqInterf) {
//        MyObjectRequest jsonObjectRequest = null;
//        LogUtil.E(UrlConstant.URI + model);
//        final String jsonstr = gson.toJson(reqBean);
//        LogUtil.E(jsonstr);
//
//        if (!reqInterf.onNetWorkReqStart(UrlConstant.URI + model, jsonstr)) {
//            BaseResBean res = new BaseResBean();
//            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
//            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
//            res.setData(jsonstr);
//            reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//            return;
//        }
//        jsonObjectRequest = new MyObjectRequest(Request.Method.POST, UrlConstant.URI + model, jsonstr, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                if (response == null) {
//                    BaseResBean res = new BaseResBean();
//                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
//                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
//                    reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, res);
//                } else {
//                    BaseResBean baseResBean = gson.fromJson(response.toString(), BaseResBean.class);
//                    reqInterf.onNetWorkReqFinish(true, UrlConstant.URI + model, baseResBean);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                BaseResBean baseResBean = new BaseResBean();
//                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
//                baseResBean.setErrorMessage(error.getMessage() == null ? "" : error.getMessage());
//                reqInterf.onNetWorkReqFinish(false, UrlConstant.URI + model, baseResBean);
//            }
//        });
//
//        jsonObjectRequest.setTag(context.getClass().getName());
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        jsonObjectRequest.setShouldCache(true);
//        mQueue.add(jsonObjectRequest);
//    }
//

}
