package com.example.makda.pentago;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.makda.pentago.boards.BoardView;

public class StartDraw extends Activity {
    BoardView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bv = new BoardView(this);
        bv.setBackgroundColor(Color.WHITE);
        setContentView(bv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}