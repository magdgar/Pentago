package com.example.makda.pentago.draw_activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.makda.pentago.boards.triangle.TriangleBoardView;

/**
 * Created by Makda on 2016-01-11.
 */
public class StartDrawTriangleGame extends Activity {

    TriangleBoardView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bv = new TriangleBoardView(this);
        bv.setBackgroundColor(Color.WHITE);
        setContentView(bv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
