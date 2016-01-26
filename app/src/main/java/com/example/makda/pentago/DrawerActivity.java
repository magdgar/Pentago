package com.example.makda.pentago;

import android.os.Bundle;
import android.widget.TextView;
import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.userStatsRequests.FavoriteBoardRequest;
import com.example.makda.pentago.network.requests.userStatsRequests.FavoritePartnerRequest;
import com.example.makda.pentago.network.requests.userStatsRequests.WonStatsRequest;
import com.example.makda.pentago.network.requests.userStatsRequests.TimeStatsRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;


/**
 * Created by Makda on 2016-01-22.
 */
public class DrawerActivity extends SpiceActivity {

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        login = this.getIntent().getStringExtra("login");
        ((TextView)findViewById(R.id.login_text)).setText(login);

        FavoritePartnerRequest favoritePartnerRequest = new FavoritePartnerRequest(login);
        getSpiceManager().execute(favoritePartnerRequest, new RequestListener<String>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.enemy_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(String s) {
                ((TextView) findViewById(R.id.enemy_text)).setText(s);
            }
        });

        FavoriteBoardRequest favoriteBoardRequest = new FavoriteBoardRequest(login);
        getSpiceManager().execute(favoriteBoardRequest, new RequestListener<String>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.board_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(String s) {
                ((TextView) findViewById(R.id.board_text)).setText(s);
            }
        });

        WonStatsRequest wonStatsRequest = new WonStatsRequest(login);
        getSpiceManager().execute(wonStatsRequest, new RequestListener<Double>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.won_stats_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(Double d) {
                d = d*100;
                ((TextView) findViewById(R.id.won_stats_text)).setText(String.valueOf(d.intValue()) + " %");
            }
        });

        TimeStatsRequest timeStatsRequest = new TimeStatsRequest(login);
        getSpiceManager().execute(timeStatsRequest, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                ((TextView) findViewById(R.id.time_text)).setText("---");
            }

            @Override
            public void onRequestSuccess(List list) {
                ((TextView) findViewById(R.id.time_text)).setText(list.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
