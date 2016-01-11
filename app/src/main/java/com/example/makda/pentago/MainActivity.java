package com.example.makda.pentago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.makda.pentago.boards.triangle.triangle.openGL.OpenGLES20Activity;
import com.example.makda.pentago.draw_activity.StartDraw;
import com.example.makda.pentago.draw_activity.StartDrawAIGame;

/**
 * Created by Makda on 2016-01-05.
 */
public class MainActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSingleGame(View view){
        Intent resIntent = new Intent(this, StartDraw.class);
        startActivity(resIntent);
    }

    public void startAIGame(View view){
        Intent resIntent = new Intent(this, StartDrawAIGame.class);
        startActivity(resIntent);
    }

    public void drawTriangleInOpenGL(View view){
        Intent resIntent = new Intent(this, OpenGLES20Activity.class);
        startActivity(resIntent);
    }
}
