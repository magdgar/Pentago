package com.example.makda.pentago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.makda.pentago.draw_activity.StartDrawTriangleGame;

public class ShapeChooseActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_choose);
    }

    public void startRectangleGame(View view){
        Intent resIntent = new Intent(this, MainActivity.class);
        startActivity(resIntent);
    }

    public void startTriangleGame(View view){
        Intent resIntent = new Intent(this, StartDrawTriangleGame.class);
        startActivity(resIntent);
    }
}
