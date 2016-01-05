package com.example.makda.pentago;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class StartDraw extends Activity {
    BoardView bv;
    public static int screenWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        Log.d("asdasd -width", String.valueOf(screenWidth));

        bv = new BoardView(this);
        bv.setBackgroundColor(Color.WHITE);
        setContentView(bv);
    }
}