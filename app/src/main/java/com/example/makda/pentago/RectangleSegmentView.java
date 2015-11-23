package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.util.Pair;
import android.view.View;
import java.util.LinkedList;

public class RectangleSegmentView extends View {

    LinkedList<Rectangle> rectangles;
    Context context;
    Pair startParams;

    public RectangleSegmentView(Context context, Pair startParams) {
        //startParam.first ->spaceFromLeft, .second ->spaceFromTop
        super(context);
        rectangles = new LinkedList<>();

        this.context = context;
        this.startParams = startParams;
        createGrid();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        displayGrid(canvas);
    }

    public void draw(Canvas canvas){
        displayGrid(canvas);
    }

    public void createGrid(){
        int gridWidth = 3;
        int gridHeight = 3;
        int rectSize = 60;
        int spacesBetweenRectangles = 5;
        int top = (int) startParams.second;
        int left;
        int right;
        int bottom;

        for(int i = 0; i < gridWidth; i++) {
            left = (int) startParams.first;
            for(int j = 0; j < gridHeight; j++) {
                right = left + rectSize;
                bottom = top + rectSize;
                Rect rect = new Rect(left, top, right, bottom);
                Rectangle r = new Rectangle(this.context, rect);
                rectangles.push(r);
                left += rectSize + spacesBetweenRectangles;
            }
            top += rectSize + spacesBetweenRectangles;
        }
    }

    public void displayGrid(Canvas canvas){
        for(Rectangle rect : rectangles){
            rect.draw(canvas);
        }
    }
}
