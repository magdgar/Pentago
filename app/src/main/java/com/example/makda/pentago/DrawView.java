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
    Context context;
    int countRectClick;
    LinkedList<Rectangle> rectangles;
    LinkedList<RectangleSegmentView> segments;

    public DrawView(Context context) {
        super(context);
        this.context = context;
        rectangles = new LinkedList<>();
        segments = new LinkedList<>();
        createBoard();
        countRectClick = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        for(RectangleSegmentView s: segments)
            s.display(canvas);
    }

    public void createBoard(){
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++){
                segments.push(new RectangleSegmentView(context, 35 + i * 215, 185 + j * 215));
            }
        for(RectangleSegmentView s : segments){
            rectangles.addAll(s.rectangles);
        }
    }

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