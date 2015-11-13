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
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

public class DrawView extends View {
    Paint paint = new Paint();
    LinkedList<Rectangle> rectangles;

    public DrawView(Context context) {
        super(context);
        rectangles = new LinkedList<>();
        createBoard();
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        displayBoard(canvas);
    }

    public void createBoard(){
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                create3x3Grid(35+ i*215, 185 +j*215);
            }
    }

    public void displayBoard(Canvas canvas){
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                display3x3Grid(canvas);
            }
    }

    public void create3x3Grid(int leftStart, int top){
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
                Rectangle r = new Rectangle(rect);
                rectangles.push(r);
                //canvas.drawRect(rect, r.getPaint());
                left += GRID_SIZE + 5;
            }
            top += GRID_SIZE + 5;
        }
    }

    public void display3x3Grid(Canvas canvas){
        for(Rectangle rect : rectangles){
            canvas.drawRect(rect.getRect(), rect.getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = Math.round(event.getX());
        int touchY = Math.round(event.getY());
        int e = event.getAction();
        switch(e){
            case MotionEvent.ACTION_DOWN:
                Log.v("asdasd", "Touching down!");
                for(Rectangle rect : rectangles){
                    if(rect.contains(touchX, touchY)){
                        rect.setColor(Color.CYAN);
                        invalidate();
                        //int color = Color.RED;
                        //paint.setColor(color);
                        //canvas.drawRect(rects, paint);
                        TODO:
                        //problem! do own class rectangle with canvas
                        Log.v("asdasd", "Touched Rectangle, start activity");
                        //Intent i = new Intent(<your activity info>);
                        //startActivity(i);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.v("asdasd", "Touching up!");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("asdasd", "Sliding your finger around on the screen.");
                break;
        }
        return true;
    }
}