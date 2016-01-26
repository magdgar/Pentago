package com.example.makda.pentago.network.requests.userStatsRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-22.
 */
public class CreatePartnerStatisticItemRequest extends RetrofitSpiceRequest<Void, PentagoApi> {
    private String login;
    private String player;

    public CreatePartnerStatisticItemRequest(String login, String player) {
        super(Void.class, PentagoApi.class);
        this.login = login;
        this.player = player;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().createPartnerStatisticItem(login, player);
    }
}
