package com.example.makda.pentago;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.makda.pentago.draw_activity.StartDrawTriangleGame;
import com.example.makda.pentago.model.User;
import com.example.makda.pentago.network.SpiceActivity;
import com.example.makda.pentago.network.requests.CreateShapeStatisticItemRequest;
import com.example.makda.pentago.network.requests.userRequests.UserRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ShapeChooseActivity extends SpiceActivity {
    SharedPreferences prefs;
    String login;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_choose);

        prefs = getSharedPreferences("login", MODE_PRIVATE);
        login = prefs.getString("name", "No name defined");
    }

    public void startRectangleGame(View view){
        Intent resIntent = new Intent(this, PlayerChooserActivity.class);
        startActivity(resIntent);

        if(login != "") {
            CreateShapeStatisticItemRequest shapeRequest = new CreateShapeStatisticItemRequest(login, "rectangle");
            getSpiceManager().execute(shapeRequest, new RequestListener<Void>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                }

                @Override
                public void onRequestSuccess(Void aVoid) {
                    Log.v("ASDASD", "DEATH!!!!!");
                }
            });
        }
    }

    public void startTriangleGame(View view){
        Intent resIntent = new Intent(this, StartDrawTriangleGame.class);
        startActivity(resIntent);

        if(login != "") {
            CreateShapeStatisticItemRequest shapeRequest = new CreateShapeStatisticItemRequest(login, "triangle");
            getSpiceManager().execute(shapeRequest, new RequestListener<Void>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                }

                @Override
                public void onRequestSuccess(Void aVoid) {
                }
            });
        }
    }
}
