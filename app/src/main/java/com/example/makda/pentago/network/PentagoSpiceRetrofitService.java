package com.example.makda.pentago.network;

/**
 * Created by Makda on 2016-01-18.
 */
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class PentagoSpiceRetrofitService extends RetrofitGsonSpiceService {
    private final String ENDPOINT = "http://192.168.1.12:8080";

    @Override
    protected String getServerUrl() {
        return ENDPOINT;
    }
}