package com.example.makda.pentago;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by Makda on 2016-01-06.
 */
public class StartDrawAIGame extends Activity {
    AIBoardView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bv = new AIBoardView(this);
        bv.setBackgroundColor(Color.WHITE);
        setContentView(bv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
