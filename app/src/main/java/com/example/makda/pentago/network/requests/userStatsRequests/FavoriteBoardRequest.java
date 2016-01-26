package com.example.makda.pentago.network.requests.userStatsRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-23.
 */

public class FavoriteBoardRequest extends RetrofitSpiceRequest<String, PentagoApi> {
    private String login;

    public FavoriteBoardRequest(String login) {
        super(String.class, PentagoApi.class);
        this.login = login;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        return getService().getFavoriteBoard(login);
    }
}
