package com.example.makda.pentago.network.requests.userRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-18.
 */

public class CreateUserRequest extends RetrofitSpiceRequest<Void, PentagoApi> {
    private String login;
    private String password;
    private String email;

    public CreateUserRequest(String login, String password, String email) {
        super(Void.class, PentagoApi.class);
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().createUser(login, password, email);
    }
}