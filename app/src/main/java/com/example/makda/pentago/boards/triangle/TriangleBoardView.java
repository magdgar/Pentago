package com.example.makda.pentago.boards.triangle;

/**
 * Created by Makda on 2016-01-11.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.makda.pentago.boards.square.Square;
import com.example.makda.pentago.boards.square.SquareSegmentView;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.makda.pentago.Utils;

public class TriangleBoardView extends ViewGroup {
    private TriangleSegmentView[] segmentViews;
    private int[][] permutations;
    TriangleSegmentView segmentView;
    private int countRectClick;
    private boolean wasPreviousActionClick;
    int touchX;

    public TriangleBoardView(Context context) {
        super(context);
        addQuaterBoards();
        segmentView = getClickedQuarterBoard(0, 0);
        createPermutation();
        wasPreviousActionClick = false;
        countRectClick = 0;
    }

    public TriangleBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addQuaterBoards();
        createPermutation();
        countRectClick = 0;
    }

    private void addQuaterBoards(){
        segmentViews = new TriangleSegmentView[]{new TriangleSegmentView(getContext())};
        for(TriangleSegmentView segmentView : segmentViews)
            addView(segmentView);
    }

    private void createPermutation(){
        permutations = new int[][]{
                {0,1,2,3,4,5,6,7,8},
                {6,3,0,7,4,1,8,5,2},
                {8,7,6,5,4,3,2,1,0},
                {2,5,8,1,4,7,0,3,6}};
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r-l;
        int height = b-t;
        int spaceFromTop = (height - width)/2;
        segmentViews[0].layout(0, spaceFromTop - 5, r / 2, spaceFromTop + width/2 -5);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchY;
        int e = event.getAction();

        switch(e){
            case MotionEvent.ACTION_DOWN:
                touchX = Math.round(event.getX());
                touchY = Math.round(event.getY());
                if(!wasPreviousActionClick)
                    checkIfRectangleMatched(touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                int x = Math.round(event.getX());
                int rotation;
                if(wasPreviousActionClick)
                    if( x != touchX) {
                        segmentView = getClickedQuarterBoard(x, Math.round(event.getY()));
                        rotation = (x - touchX > 0) ? 90 : -90;
                        segmentView.setRotation(segmentView.getRotation() + rotation);
                        segmentView.invalidate();
                        wasPreviousActionClick = false;
                    }
                break;
        }
        return true;
    }

    private void checkIfRectangleMatched(int touchX, int touchY){
        segmentView = getClickedQuarterBoard(touchX, touchY);
        touchY -= segmentView.getTop();
        touchX -= segmentView.getLeft();
        for(Square rect : segmentView.squares) {
            if (rect.contains(touchX, touchY)) {
                int properIndex = permutations[segmentView.getPermutationID()][segmentView.squares.indexOf(rect)];
                rect = segmentView.squares.get(properIndex);
                changeRectColor(rect);
                rect.setIsSelected(true);
                segmentView.invalidate();
                wasPreviousActionClick = true;
            }
        }
    }

    private TriangleSegmentView getClickedQuarterBoard(int touchX, int touchY){
        if(touchY < getHeight()/2)
            return (touchX < getWidth()/2)? segmentViews[0] : segmentViews[1];
        else
            return (touchX < getWidth()/2)? segmentViews[2] : segmentViews[3];
    }

    private void changeRectColor(Square rect){
        if(!rect.isSelected()) {
            if (countRectClick % 2 == 1)
                rect.setColor(Color.CYAN);
            else
                rect.setColor(Color.MAGENTA);
            countRectClick++;
        }
    }
}