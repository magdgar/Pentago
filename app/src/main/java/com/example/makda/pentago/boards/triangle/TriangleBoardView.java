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
        segmentView = getClickedSegment(0, 0);
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
        segmentViews = new TriangleSegmentView[]{new TriangleSegmentView(getContext()),
                new TriangleSegmentView(getContext()), new TriangleSegmentView(getContext()),
                new TriangleSegmentView(getContext())};
        for(TriangleSegmentView segmentView : segmentViews)
            addView(segmentView);
    }

    private void createPermutation(){
        permutations = new int[][]{
                {0,1,2,3,4,5,6,7,8,9},
                {3,6,8,9,2,5,7,1,4,0},
                {9,7,4,0,8,5,1,6,2,3}};
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r-l;
        int height = b-t;
        int spaceFromTop = (height - width)/2;
        segmentViews[0].layout(0, spaceFromTop - 5, r / 2, spaceFromTop + width/2 +20);
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
                        segmentView = getClickedSegment(x, Math.round(event.getY()));
                        rotation = (x - touchX > 0) ? -1 : 1;
                        segmentView.addToPermutationID(rotation);
                        refreshRectangleColors();
                        segmentView.invalidate();
                        wasPreviousActionClick = false;
                    }
                break;
        }
        return true;
    }

    private void checkIfRectangleMatched(int touchX, int touchY){
        segmentView = getClickedSegment(touchX, touchY);
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

    private TriangleSegmentView getClickedSegment(int touchX, int touchY){
        if(touchY < getHeight()/2)
            return (touchX < getWidth()/2)? segmentViews[0] : segmentViews[1];
        else
            return (touchX < getWidth()/2)? segmentViews[2] : segmentViews[3];
    }

    private void refreshRectangleColors(){
        int colors[] = new int[10];
        int len = segmentView.squares.size();
        for(int i=0; i<len; i++)
            colors[i] = segmentView.squares.get(i).getPaint().getColor();
        for(int i=0; i<len; i++){
            segmentView.squares.get(permutations[segmentView.permutationID][i]).setColor(colors[i]);
        }
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