package com.example.makda.pentago;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.makda.pentago.boards.triangle.triangle.openGL.OpenGLES20Activity;
import com.example.makda.pentago.draw_activity.StartDraw;
import com.example.makda.pentago.draw_activity.StartDrawAIGame;
import com.example.makda.pentago.draw_activity.StartDrawRandoxGame;
import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.CreatePartnerStatisticItemRequest;
import com.example.makda.pentago.network.requests.CreateShapeStatisticItemRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Makda on 2016-01-05.
 */
public class PlayerChooserActivity extends SpiceActivity {
    SharedPreferences prefs;
    String login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);

        prefs = getSharedPreferences("login", MODE_PRIVATE);
        login = prefs.getString("name", "No name defined");
    }

    public void startSingleGame(View view){
        Intent resIntent = new Intent(this, StartDraw.class);
        startActivity(resIntent);

        if(login != "") {
            CreatePartnerStatisticItemRequest partnerRequest = new CreatePartnerStatisticItemRequest(login, "friend");
            getSpiceManager().execute(partnerRequest, new RequestListener<Void>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                }

                @Override
                public void onRequestSuccess(Void aVoid) {
                }
            });
        }
    }

    public void startAIGame(View view){
        Intent resIntent = new Intent(this, StartDrawAIGame.class);
        startActivity(resIntent);

        if(login != "") {
            CreatePartnerStatisticItemRequest partnerRequest = new CreatePartnerStatisticItemRequest(login, "ai");
            getSpiceManager().execute(partnerRequest, new RequestListener<Void>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                }

                @Override
                public void onRequestSuccess(Void aVoid) {
                }
            });
        }
    }

    public void startRandoxGame(View view){
        Intent resIntent = new Intent(this, StartDrawRandoxGame.class);
        startActivity(resIntent);

        if(login != "") {
            CreatePartnerStatisticItemRequest partnerRequest = new CreatePartnerStatisticItemRequest(login, "randox");
            getSpiceManager().execute(partnerRequest, new RequestListener<Void>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                }

                @Override
                public void onRequestSuccess(Void aVoid) {
                }
            });
        }
    }

    public void drawTriangleInOpenGL(View view){
        Intent resIntent = new Intent(this, OpenGLES20Activity.class);
        startActivity(resIntent);
    }
}
