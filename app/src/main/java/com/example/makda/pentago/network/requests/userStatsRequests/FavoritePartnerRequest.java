package com.example.makda.pentago.network.requests.userStatsRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-22.
 */
public class FavoritePartnerRequest extends RetrofitSpiceRequest<String, PentagoApi> {
    private String login;

    public FavoritePartnerRequest(String login) {
        super(String.class, PentagoApi.class);
        this.login = login;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        return getService().getFavoritePartner(login);
    }
}