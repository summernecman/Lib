package com.summer.lib.network.requst;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${viwmox} on 2016-11-16.
 */
public class MyObjectRequest extends JsonObjectRequest {

    private String mHeader;
    private Map<String, String> sendHeader = new HashMap<String, String>(1);

    private Response.Listener<JSONObject> mListener;

    public MyObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public MyObjectRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public MyObjectRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public MyObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public MyObjectRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            if (ValueConstant.cookieFromResponse != null) {
                return super.parseNetworkResponse(response);
            }
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            mHeader = response.headers.toString();
            Log.w("LOG", "get headers in parseNetworkResponse " + response.headers.toString());
            //使用正则表达式从reponse的头中提取cookie内容的子串
            Pattern pattern = Pattern.compile("Set-Cookie.*?;");
            Matcher m = pattern.matcher(mHeader);
            if (m.find()) {
                ValueConstant.cookieFromResponse = m.group();
                Log.w("LOG", "cookie sender server " + ValueConstant.cookieFromResponse);
            }
            //去掉cookie末尾的分号
            ValueConstant.cookieFromResponse = ValueConstant.cookieFromResponse.substring(11, ValueConstant.cookieFromResponse.length() - 1);
            Log.w("LOG", "cookie substring " + ValueConstant.cookieFromResponse);
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            try {
                return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
        return null;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    @Override
    public byte[] getBody() {
        LogUtil.E("###" + new String(super.getBody()));
        return super.getBody();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        sendHeader.put("Cookie", ValueConstant.cookieFromResponse);
        return sendHeader;
    }

}
