package com.example.makda.pentago.boards.triangle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by Makda on 2016-01-08.
 */
public class StartDrawTriangleBoardActivity extends Activity {
    TriangleSegmentView ts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ts = new TriangleSegmentView(this);
        ts.setBackgroundColor(Color.WHITE);
        // chyba dobry kierunek ale nie dziala:(
        //ts.setLayoutParams(new ViewGroup.LayoutParams(250, 150));
        setContentView(ts);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
