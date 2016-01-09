package com.example.makda.pentago.boards.square;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.makda.pentago.Utils;

import java.util.Random;

/**
 * Created by Makda on 2016-01-06.
 */
public class AIBoardView extends BoardView{
    private SquareSegmentView[] squareSegmentViews;
    private int[][] permutations;
    SquareSegmentView squareSegmentView;
    private int countRectClick;
    private boolean wasPreviousActionClick;
    int touchX;

    public AIBoardView(Context context) {
        super(context);
        addQuaterBoards();
        squareSegmentView = getClickedQuarterBoard(0, 0);
        createPermutation();
        wasPreviousActionClick = false;
        countRectClick = 0;
    }

    public AIBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addQuaterBoards();
        createPermutation();
        countRectClick = 0;
    }

    private void addQuaterBoards(){
        squareSegmentViews = new SquareSegmentView[]{new SquareSegmentView(getContext()), new SquareSegmentView(getContext()),
                new SquareSegmentView(getContext()), new SquareSegmentView(getContext())};
        for(SquareSegmentView squareSegmentView : squareSegmentViews)
            addView(squareSegmentView);
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
        squareSegmentViews[0].layout(0, spaceFromTop - 5, r / 2, spaceFromTop + width/2 -5);
        squareSegmentViews[1].layout(r / 2, spaceFromTop - 5, r, spaceFromTop + width/2 -5);
        squareSegmentViews[2].layout(0, spaceFromTop + width/2, r / 2, spaceFromTop + width);
        squareSegmentViews[3].layout(r / 2,spaceFromTop + width/2, r, spaceFromTop + width);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchY;
        int e = event.getAction();

        switch(e){
            case MotionEvent.ACTION_DOWN:
                touchX = Math.round(event.getX());
                touchY = Math.round(event.getY());
                //TODO fix that empty space can be clicked
                if(!wasPreviousActionClick)
                    markRectangleIfMatched(touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                int x = Math.round(event.getX());
                int rotation;
                if(wasPreviousActionClick)
                    if( x != touchX) {
                        squareSegmentView = getClickedQuarterBoard(x, Math.round(event.getY()));
                        rotation = (x - touchX > 0) ? 90 : -90;
                        squareSegmentView.setRotation(squareSegmentView.getRotation() + rotation);
                        squareSegmentView.invalidate();
                        wasPreviousActionClick = false;
                        makeAImove();
                    }
                break;
        }
        return true;
    }

    private void makeAImove(){
        Random rand =  new Random();
        while( ! wasPreviousActionClick ) {
            int x = rand.nextInt(Utils.getScreenWidth(getContext()));
            int y = rand.nextInt(Utils.getScreenHeight(getContext()));
            markRectangleIfMatched(x, y);
        }
        squareSegmentView = squareSegmentViews[rand.nextInt(4)];

        int rotation = (rand.nextInt(2) - 1 > 0) ? 90 : -90;
        squareSegmentView.setRotation(squareSegmentView.getRotation() + rotation);
        squareSegmentView.invalidate();
        wasPreviousActionClick = false;
    }

    private void markRectangleIfMatched(int touchX, int touchY){
        squareSegmentView = getClickedQuarterBoard(touchX, touchY);
        touchY -= squareSegmentView.getTop();
        touchX -= squareSegmentView.getLeft();
        for(Square rect : squareSegmentView.squares) {
            if (rect.contains(touchX, touchY)) {
                int properIndex = permutations[squareSegmentView.getPermutationID()][squareSegmentView.squares.indexOf(rect)];
                rect = squareSegmentView.squares.get(properIndex);
                changeRectColor(rect);
                rect.isSelected = true;
                squareSegmentView.invalidate();
                wasPreviousActionClick = true;
            }
        }

    }

    private SquareSegmentView getClickedQuarterBoard(int touchX, int touchY){
        if(touchY < getHeight()/2)
            return (touchX < getWidth()/2)? squareSegmentViews[0] : squareSegmentViews[1];
        else
            return (touchX < getWidth()/2)? squareSegmentViews[2] : squareSegmentViews[3];
    }

    private void changeRectColor(Square rect){
        if(!rect.isSelected) {
            if (countRectClick % 2 == 1)
                rect.setColor(Color.CYAN);
            else
                rect.setColor(Color.YELLOW);
            countRectClick++;
        }
    }
}
