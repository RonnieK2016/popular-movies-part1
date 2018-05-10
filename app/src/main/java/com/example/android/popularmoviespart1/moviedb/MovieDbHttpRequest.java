package com.example.android.popularmoviespart1.moviedb;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.android.popularmoviespart1.listeners.HttpResponseListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by angelov on 5/6/2018.
 */

public class MovieDbHttpRequest<T> extends Request<T> {

    private static final String TAG = MovieDbHttpRequest.class.getSimpleName();
    private Map<String, String> mParams;
    private HttpResponseListener<T> mListener;

    public MovieDbHttpRequest(int method, String url, Map<String, String> params, HttpResponseListener<T> listener) {
        super(method, url, listener);
        this.mParams = params;
        this.mListener = listener;

        setRetryPolicy(new DefaultRetryPolicy(5000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setShouldCache(false);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonResponse = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
            Gson gSonParser = gsonBuilder.create();
            T parsedJsonResponse = gSonParser.fromJson(jsonResponse, new TypeToken<MovieDbHttpResponse>() {
            }.getType());

            return Response.success(parsedJsonResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if(mListener != null) {
            mListener.onResponse(response);
        }
    }
}
