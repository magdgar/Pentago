package com.example.makda.pentago.network.requests.userRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-18.
 */
public class DeleteUserRequest extends RetrofitSpiceRequest<Void, PentagoApi> {
    private String name;
    private String password;

    public DeleteUserRequest(String name, String password) {
        super(Void.class, PentagoApi.class);
        this.name = name;
        this.password = password;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().deleteUser(name, password);
    }
}