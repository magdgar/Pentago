package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Makda on 2015-11-16.
 */
public class RectangleSegmentView extends View {

    LinkedList<Rectangle> rectangles;
    int  spaceFromleft;
    int spaceFromTop;

    public RectangleSegmentView(Context context) {
        super(context);
        //spaceFromleft = left;
        //spaceFromTop = top;
        spaceFromleft = 25;
        spaceFromTop = 100;

        rectangles = new LinkedList<>();
        createRectangles();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //paint.setColor(Color.BLACK);
        super.onDraw(canvas);
        display(canvas);
    }

     public void createRectangles(){
        int GRID_WIDTH = 3;
        int GRID_HEIGHT = 3;
        int GRID_SIZE = 60;
        int top = spaceFromTop;
        // int top = 0;
        int left;
        int right;
        int bottom;

        for(int i = 0; i < GRID_WIDTH; i++) {
            left = spaceFromleft;
            //left = 0;
            for(int j = 0; j < GRID_HEIGHT; j++) {
                right = left + GRID_SIZE;
                bottom = top + GRID_SIZE;
                Rect rect = new Rect(left, top, right, bottom);
                Rectangle r = new Rectangle(rect);
                rectangles.push(r);
                left += GRID_SIZE + 5;
            }
            top += GRID_SIZE + 5;
        }
    }
    public void display(Canvas canvas){
        for(Rectangle rect : rectangles){
            canvas.drawRect(rect.getRect(), rect.getPaint());
        }
    }
}
