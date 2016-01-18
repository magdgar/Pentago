package com.example.makda.pentago.draw_activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.makda.pentago.R;

/**
 * Created by Makda on 2016-01-19.
 */
public class StartDrawRandoxGame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.square_board_randox_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
