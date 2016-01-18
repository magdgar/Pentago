package com.example.makda.pentago.network.requests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Makda on 2016-01-18.
 */
public class UpdateUserRequest extends RetrofitSpiceRequest<Void, PentagoApi> {
    private String name;
    private String password;
    private String newPassword;

    public UpdateUserRequest(String name, String password, String newPassword) {
        super(Void.class, PentagoApi.class);
        this.name = name;
        this.password = password;
        this.newPassword = newPassword;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().updatePassword(name, password, newPassword);
    }
}