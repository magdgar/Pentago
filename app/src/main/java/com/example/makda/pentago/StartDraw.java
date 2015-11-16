package com.example.makda.pentago;

/**
 * Created by Makda on 2015-11-12.
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

public class StartDraw extends Activity {
    DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);

        //drawView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        setContentView(drawView);

    }
}