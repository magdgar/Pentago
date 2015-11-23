package com.example.makda.pentago;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class StartDraw extends Activity {
    BoardView bv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bv = new BoardView(this);
        bv.setBackgroundColor(Color.WHITE);
        setContentView(bv);
    }
}