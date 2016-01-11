package com.example.makda.pentago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.makda.pentago.boards.square.SquareSegmentView;
import com.example.makda.pentago.boards.triangle.TriangleBoardView;
import com.example.makda.pentago.boards.triangle.TriangleSegmentView;
import com.example.makda.pentago.boards.triangle.triangle.openGL.OpenGLES20Activity;

public class ShapeChooseActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_choose);
    }

    public void startRectangleGame(View view){
        Intent resIntent = new Intent(this, MainActivity.class);
        startActivity(resIntent);
    }

    public void startTriangleGame(View view){
        Intent resIntent = new Intent(this, StartDrawTriangleGame.class);
        startActivity(resIntent);
    }
}
