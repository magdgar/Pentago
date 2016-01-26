package com.example.makda.pentago.network.requests.adminRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by Makda on 2016-01-27.
 */
public class UserBadgesRequest extends RetrofitSpiceRequest<List, PentagoApi> {

    public UserBadgesRequest() {
        super(List.class, PentagoApi.class);
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        return getService().userBagde();
    }
}
