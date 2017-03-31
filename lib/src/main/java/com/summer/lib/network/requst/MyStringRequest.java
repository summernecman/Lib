package com.summer.lib.network.requst;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.summer.lib.constant.ValueConstant;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${viwmox} on 2016-11-15.
 */
public class MyStringRequest extends StringRequest {

    private String mHeader;
    private Map<String, String> sendHeader = new HashMap<String, String>(1);

    private Response.Listener<JSONObject> mListener;


    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public MyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
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
                Log.w("LOG", "cookie from server " + ValueConstant.cookieFromResponse);
            }
            //去掉cookie末尾的分号
            ValueConstant.cookieFromResponse = ValueConstant.cookieFromResponse.substring(11, ValueConstant.cookieFromResponse.length() - 1);
            Log.w("LOG", "cookie substring " + ValueConstant.cookieFromResponse);
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        sendHeader.put("Cookie", ValueConstant.cookieFromResponse);
        return sendHeader;
    }
}
