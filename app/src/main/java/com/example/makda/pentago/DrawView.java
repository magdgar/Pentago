package com.example.makda.pentago;

/**
 * Created by Makda on 2015-11-12.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class DrawView extends View {
    Paint paint = new Paint();
    LinkedList<Rect> rectangles;

    public DrawView(Context context) {
        super(context);
        rectangles = new LinkedList<>();
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        displayBoard(canvas);
    }

    public void displayBoard(Canvas canvas){
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                display3x3Grid(canvas, 35+ i*215, 185 +j*215);
            }
    }

    public void display3x3Grid(Canvas canvas, int leftStart, int top){
        int GRID_WIDTH = 3;
        int GRID_HEIGHT = 3;
        int GRID_SIZE = 60;
        int left;
        int right;
        int bottom;

        for(int i = 0; i < GRID_WIDTH; i++) {
            left = leftStart;
            for(int j = 0; j < GRID_HEIGHT; j++) {
                right = left + GRID_SIZE;
                bottom = top + GRID_SIZE;
                Rect rect = new Rect(left, top, right, bottom);
                rectangles.push(rect);
                canvas.drawRect(rect, paint);
                left += GRID_SIZE + 5;
            }
            top += GRID_SIZE + 5;
        }
    }
}