package com.example.makda.pentago;

import android.os.Bundle;
import android.widget.TextView;

import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.adminRequests.BestPlayersRequest;
import com.example.makda.pentago.network.requests.adminRequests.EnemysRequest;
import com.example.makda.pentago.network.requests.adminRequests.UserBadgesRequest;
import com.example.makda.pentago.network.requests.userStatsRequests.AdminsRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

/**
 * Created by Makda on 2016-01-25.
 */
public class DrawerAdminActivity extends SpiceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_admin_layout);

        AdminsRequest adminsRequest = new AdminsRequest();
        getSpiceManager().execute(adminsRequest, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.admins_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(List list) {
                ((TextView) findViewById(R.id.admins_text)).setText(list.toString());
            }
        });

        BestPlayersRequest bestPlayersRequest = new BestPlayersRequest();
        getSpiceManager().execute(bestPlayersRequest, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.best_players_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(List list) {
                ((TextView) findViewById(R.id.best_players_text)).setText(list.toString());
            }
        });


        EnemysRequest enemysRequest = new EnemysRequest();
        getSpiceManager().execute(enemysRequest, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.enemys_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(List list) {
                ((TextView) findViewById(R.id.enemys_text)).setText(list.toString());
            }
        });

        UserBadgesRequest userBadgesRequest = new UserBadgesRequest();
        getSpiceManager().execute(userBadgesRequest, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.user_badges_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(List list) {
                ((TextView) findViewById(R.id.user_badges_text)).setText(list.toString());
            }
        });
    }
}