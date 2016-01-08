package com.example.makda.pentago.boards.triangle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.makda.pentago.boards.square.BoardView;

/**
 * Created by Makda on 2016-01-08.
 */
public class StartDrawTriangleBoardActivity extends Activity {
    TriangleSegment ts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ts = new TriangleSegment(this);
        ts.setBackgroundColor(Color.WHITE);
        setContentView(ts);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
