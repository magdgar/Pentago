package com.example.makda.pentago.boards.triangle;

/**
 * Created by Makda on 2016-01-11.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;



public class TriangleBoardView extends ViewGroup {
    AIPlayer player;
    private TriangleSegmentView[] segmentViews;
    private RotatedTriangleSegmentView[] rotatedSegmentViews;
    TriangleSegmentView segmentView;
    private boolean wasPreviousActionClick;
    int touchX;

    public TriangleSegmentView[] getSegmentViews() {
        return segmentViews;
    }

    public RotatedTriangleSegmentView[] getRotatedSegmentViews() {
        return rotatedSegmentViews;
    }

    public TriangleBoardView(Context context) {
        super(context);
        addQuaterBoards();
        segmentView = getClickedSegment(0, 0);
        wasPreviousActionClick = false;
    }

    public void setPlayer(AIPlayer player) {
        this.player = player;
    }

    public TriangleBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addQuaterBoards();
    }

    private void addQuaterBoards(){
        segmentViews = new TriangleSegmentView[]{new TriangleSegmentView(getContext()),
                new TriangleSegmentView(getContext()), new TriangleSegmentView(getContext())};
        rotatedSegmentViews = new RotatedTriangleSegmentView[]{new RotatedTriangleSegmentView(getContext()),
                new RotatedTriangleSegmentView(getContext()), new RotatedTriangleSegmentView(getContext())};

        for(TriangleSegmentView segmentView : segmentViews)
            addView(segmentView);

        for(RotatedTriangleSegmentView segmentView :rotatedSegmentViews)
            addView(segmentView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r-l;
        rotatedSegmentViews[0].layout(10, 10, r / 2, width / 2 + 20);
        segmentViews[0].layout(r / 2 + 5, 50, r, width / 2 + 60);

        rotatedSegmentViews[1].layout(10, width / 2 + 30, r / 2, width + 45);
        segmentViews[1].layout(r / 2 + 5, width / 2 + 70, r, width + 90);

        rotatedSegmentViews[2].layout(10, width + 60, r / 2, b - 70);
        segmentViews[2].layout(r / 2 + 5, width + 100, r, b);
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
                        rotation = (x - touchX > 0) ? 1 : -1;
                        segmentView.addToPermutationID(rotation);
                        refreshRectangleColors();
                        segmentView.invalidate();
                        wasPreviousActionClick = false;
                        player.makeMoves();
                    }
                break;
        }
        return true;
    }

    private void checkIfRectangleMatched(int touchX, int touchY){
        segmentView = getClickedSegment(touchX, touchY);
        touchY -= segmentView.getTop();
        touchX -= segmentView.getLeft();

        for (Square rect : segmentView.squares) {
            if (rect.contains(touchX, touchY)) {
                changeRectColor(rect);
                rect.setIsSelected(true);
                segmentView.invalidate();
                wasPreviousActionClick = true;;
            }
        }
    }

    private TriangleSegmentView getClickedSegment(int touchX, int touchY){
        int middle = rotatedSegmentViews[0].getRight();
        if(touchX < middle) {
            if (touchY < rotatedSegmentViews[0].getBottom())
                return rotatedSegmentViews[0];
            else if (touchY < rotatedSegmentViews[1].getBottom())
                return rotatedSegmentViews[1];
            return rotatedSegmentViews[2];
        }else if (touchY < segmentViews[0].getBottom())
                return segmentViews[0];
            else if(touchY < segmentViews[1].getBottom())
                return  segmentViews[1];
        return segmentViews[2];
    }

    public void refreshRectangleColors(){
        int colors[] = new int[10];
        int len = segmentView.squares.size();
        for(int i=0; i<len; i++)
            colors[i] = segmentView.squares.get(segmentView.permutations[segmentView.permutationID][i]).getPaint().getColor();
        for(int i=0; i<len; i++){
            segmentView.squares.get(i).setColor(colors[i]);
        }
    }

    private void changeRectColor(Square rect){
        if(!rect.isSelected()) {
            rect.setColor(Color.CYAN);
        }
    }
}