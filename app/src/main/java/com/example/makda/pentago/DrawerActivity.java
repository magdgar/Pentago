package com.example.makda.pentago;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.FavoriteBoardRequest;
import com.example.makda.pentago.network.requests.FavoritePartnerRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;


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
                Log.d("reqResString", s);
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
                Log.d("reqResString", s);
                ((TextView) findViewById(R.id.board_text)).setText(s);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
