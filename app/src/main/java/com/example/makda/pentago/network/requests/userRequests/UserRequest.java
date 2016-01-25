package com.example.makda.pentago.network.requests.userRequests;

import com.example.makda.pentago.model.User;
import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-18.
 */
public class UserRequest extends RetrofitSpiceRequest<Boolean, PentagoApi> {
    private String name;
    private String password;

    public UserRequest(String name, String password) {
        super(Boolean.class, PentagoApi.class);
        this.name = name;
        this.password = password;
    }

    @Override
    public Boolean loadDataFromNetwork() throws Exception {
        return getService().getUser(name, password);
    }
}