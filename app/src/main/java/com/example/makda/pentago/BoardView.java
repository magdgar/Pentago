package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

public class BoardView extends ViewGroup {
    private QuarterBoard[] quarterBoards;
    private int[][] permutations;
    QuarterBoard quarterBoard;
    private int countRectClick;
    private boolean wasPreviousActionClick;
    int touchX;

    public BoardView(Context context) {
        super(context);
        addQuaterBoards();
        quarterBoard = getClickedQuarterBoard(0, 0);
        createPermutation();
        wasPreviousActionClick = false;
        countRectClick = 0;
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addQuaterBoards();
        createPermutation();
        countRectClick = 0;
    }

    private void addQuaterBoards(){
        quarterBoards = new QuarterBoard[]{new QuarterBoard(getContext()), new QuarterBoard(getContext()),
                new QuarterBoard(getContext()), new QuarterBoard(getContext())};
        for(QuarterBoard quarterBoard : quarterBoards)
            addView(quarterBoard);
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
        int x = Utils.getScreenWidth(getContext());
        quarterBoards[0].layout(0, 145, r / 2, b / 2 );
        quarterBoards[1].layout(r / 2, 145, r , b / 2 );
        quarterBoards[2].layout(0, b / 2, r / 2 , b - 145);
        quarterBoards[3].layout(r / 2, b / 2, r, b - 145);
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
                        Log.d("sth", String.valueOf(x));
                        rotation = (x - touchX > 0) ? 90 : -90;
                        quarterBoard.setRotation(quarterBoard.getRotation() + rotation);
                        quarterBoard.invalidate();
                        wasPreviousActionClick = false;
                    }
                break;
        }
        return true;
    }

    private void checkIfRectangleMatched(int touchX, int touchY){
        quarterBoard = getClickedQuarterBoard(touchX, touchY);
        touchY -= quarterBoard.getTop();
        touchX -= quarterBoard.getLeft();
        for(Rectangle rect :quarterBoard.rectangles) {
            if (rect.contains(touchX, touchY)) {
                int properIndex = permutations[quarterBoard.getPermutationID()][quarterBoard.rectangles.indexOf(rect)];
                Log.d("index", String.valueOf(quarterBoard.rectangles.indexOf(rect)));
                Log.d("permutation id", String.valueOf(quarterBoard.getPermutationID()));
                rect = quarterBoard.rectangles.get(properIndex);
                changeRectColor(rect);
                rect.isSelected = true;
                quarterBoard.invalidate();
            }
        }
        wasPreviousActionClick = true;
    }

    private QuarterBoard getClickedQuarterBoard(int touchX, int touchY){
        if(touchY < getHeight()/2)
            return (touchX < getWidth()/2)? quarterBoards[0] : quarterBoards[1];
        else
            return (touchX < getWidth()/2)? quarterBoards[2] : quarterBoards[3];
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