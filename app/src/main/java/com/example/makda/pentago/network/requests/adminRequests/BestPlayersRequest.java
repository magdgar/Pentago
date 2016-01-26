package com.example.makda.pentago.network.requests.adminRequests;

import com.example.makda.pentago.network.PentagoApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by Makda on 2016-01-25.
 */
public class BestPlayersRequest extends RetrofitSpiceRequest<List, PentagoApi> {


        public BestPlayersRequest(){
        super(List.class,PentagoApi.class);
        }

@Override
public List loadDataFromNetwork()throws Exception{
        return getService().bestPlayers();
    }
}