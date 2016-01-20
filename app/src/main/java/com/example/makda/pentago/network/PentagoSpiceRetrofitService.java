package com.example.makda.pentago.network;

/**
 * Created by Makda on 2016-01-18.
 */
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Maciek on 05-Jul-15.
 */
public class PentagoSpiceRetrofitService extends RetrofitGsonSpiceService {
    private final String ENDPOINT = "http://79.185.216.78:8080";

    @Override
    protected String getServerUrl() {
        return ENDPOINT;
    }
}