package com.example.makda.pentago.draw_activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.makda.pentago.R;
import com.example.makda.pentago.boards.square.BoardView;

public class StartDraw extends Activity {
    BoardView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.square_board_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}