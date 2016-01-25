package com.example.makda.pentago.network.requests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-25.
 */
public class WonStatsRequest extends RetrofitSpiceRequest<Double, PentagoApi> {
    private String login;

    public WonStatsRequest(String login) {
        super(Double.class, PentagoApi.class);
        this.login = login;
    }

    @Override
    public Double loadDataFromNetwork() throws Exception {
        return getService().getWonStats(login);
    }
}