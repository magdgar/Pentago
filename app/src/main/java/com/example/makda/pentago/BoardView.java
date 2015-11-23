package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.LinkedList;

public class BoardView extends View {
    LinkedList<RectangleSegmentView> listOfSegments;
    LinkedList<Rectangle> rectangles;
    Context context;
    private int countRectClick;


    public BoardView(Context context) {
        super(context);
        listOfSegments = new LinkedList<>();
        rectangles =  new LinkedList<>();

        this.context = context;
        countRectClick = 0;
        createGrid();
    }

    public void createGrid(){
        RectangleSegmentView rsw;

        for(int i=30; i<250; i = i+210 )
            for(int j=150; j<370; j = j+210) {
                rsw = new RectangleSegmentView(context, new Pair(i, j));
                rectangles.addAll(0, rsw.rectangles);
                listOfSegments.add(rsw);
            }
    }

    @Override
     public void draw(Canvas canvas) {
        for(RectangleSegmentView r : listOfSegments)
            r.displayGrid(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = Math.round(event.getX());
        int touchY = Math.round(event.getY());
        int e = event.getAction();

        switch(e){
            case MotionEvent.ACTION_DOWN:
                for(Rectangle rect : rectangles)
                    checkIfRectangleMatched(rect, touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                Log.v("asdasd", "Sliding your finger around on the screen.");
                break;
        }
        return true;
    }

    private void checkIfRectangleMatched(Rectangle rect, int touchX, int touchY){
        if(rect.contains(touchX, touchY)){
            changeRectColor(rect);
            rect.isSelected = true;
            invalidate();
        }
    }

    private void changeRectColor(Rectangle rect){
        if(!rect.isSelected) {
            if (countRectClick % 2 == 1)
                rect.setColor(Color.CYAN);
            else
                rect.setColor(Color.MAGENTA);
            countRectClick++;
        }
    }
}
