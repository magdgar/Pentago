package com.example.makda.pentago.boards.square;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.makda.pentago.Utils;
import com.example.makda.pentago.boards.square.QuarterBoard;
import com.example.makda.pentago.boards.square.Rectangle;

import java.util.Random;

/**
 * Created by Makda on 2016-01-06.
 */
public class AIBoardView extends ViewGroup {
    private QuarterBoard[] quarterBoards;
    private int[][] permutations;
    QuarterBoard quarterBoard;
    private int countRectClick;
    private boolean wasPreviousActionClick;
    int touchX;

    public AIBoardView(Context context) {
        super(context);
        addQuaterBoards();
        quarterBoard = getClickedQuarterBoard(0, 0);
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
        quarterBoards[0].layout(0, 145, r / 2, b / 2 );
        quarterBoards[1].layout(r / 2, 145, r, b / 2);
        quarterBoards[2].layout(0, b / 2, r / 2, b - 145);
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
                //TODO fix that empty space can be clicked
                if(!wasPreviousActionClick)
                    markRectangleIfMatched(touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                int x = Math.round(event.getX());
                int rotation;
                if(wasPreviousActionClick)
                    if( x != touchX) {
                        quarterBoard = getClickedQuarterBoard(x, Math.round(event.getY()));
                        rotation = (x - touchX > 0) ? 90 : -90;
                        quarterBoard.setRotation(quarterBoard.getRotation() + rotation);
                        quarterBoard.invalidate();
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
        quarterBoard = quarterBoards[rand.nextInt(4)];

        int rotation = (rand.nextInt(2) - 1 > 0) ? 90 : -90;
        quarterBoard.setRotation(quarterBoard.getRotation() + rotation);
        quarterBoard.invalidate();
        wasPreviousActionClick = false;
    }

    private void markRectangleIfMatched(int touchX, int touchY){
        quarterBoard = getClickedQuarterBoard(touchX, touchY);
        touchY -= quarterBoard.getTop();
        touchX -= quarterBoard.getLeft();
        for(Rectangle rect :quarterBoard.rectangles) {
            if (rect.contains(touchX, touchY)) {
                int properIndex = permutations[quarterBoard.getPermutationID()][quarterBoard.rectangles.indexOf(rect)];
                rect = quarterBoard.rectangles.get(properIndex);
                changeRectColor(rect);
                rect.isSelected = true;
                quarterBoard.invalidate();
                wasPreviousActionClick = true;
            }
        }

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
                rect.setColor(Color.YELLOW);
            countRectClick++;
        }
    }
}
