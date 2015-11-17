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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

public class DrawView extends ViewGroup {
    Paint paint = new Paint();
    Context context;
    int countRectClick;
    LinkedList<Rectangle> rectangles;
    LinkedList<RectangleSegmentView> segments;

    public DrawView(Context context) {
        super(context);
        this.context = context;
        createBoard();
        countRectClick = 0;
    }

    public void createBoard(){
        rectangles = new LinkedList<>();
        segments = new LinkedList<>();

        for(int j=0;j<4;j++)
            segments.push(new RectangleSegmentView(context));

        for(RectangleSegmentView s : segments) {
            addView(s);
            rectangles.addAll(s.rectangles);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        segments.get(0).layout(0, 0, r / 2, b / 2);
        segments.get(1).layout(r / 2, 0, r, b / 2);
        segments.get(2).layout(0, b / 2, r / 2, b);
        segments.get(3).layout(r / 2, b / 2, r, b);
    }

//    @Override
//    public void onDraw(Canvas canvas) {
//        paint.setColor(Color.BLACK);
//        for(RectangleSegmentView s: segments)
//            s.display(canvas);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = Math.round(event.getX());
        int touchY = Math.round(event.getY());
        int e = event.getAction();
        switch(e){
            case MotionEvent.ACTION_DOWN:
                for(Rectangle rect : rectangles){
                    if(rect.contains(touchX, touchY)){

                        if(!rect.isSelected) {
                            if (countRectClick % 2 == 1)
                                rect.setColor(Color.CYAN);
                            else
                                rect.setColor(Color.MAGENTA);
                            countRectClick++;
                        }
                        rect.isSelected = true;
                        invalidate();
                        TODO:
                        //problem! do own class rectangle with canvas
                        Log.v("asdasd", "Touched Rectangle, start activity");
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("asdasd", "Sliding your finger around on the screen.");
                break;
        }
        return true;
    }
}