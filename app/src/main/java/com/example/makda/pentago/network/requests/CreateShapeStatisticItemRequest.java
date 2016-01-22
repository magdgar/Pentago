package com.example.makda.pentago.network.requests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-22.
 */
public class CreateShapeStatisticItemRequest extends RetrofitSpiceRequest<Void, PentagoApi> {
    private String login;
    private String shape;

    public CreateShapeStatisticItemRequest(String login, String shape) {
        super(Void.class, PentagoApi.class);
        this.login = login;
        this.shape = shape;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().createShapeStatisticItem(login, shape);
    }
}
