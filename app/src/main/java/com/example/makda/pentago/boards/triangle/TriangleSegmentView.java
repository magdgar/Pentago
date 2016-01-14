package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.makda.pentago.Utils;


import java.util.ArrayList;
import java.util.LinkedList;


public class TriangleSegmentView extends View {
    LinkedList<Square> squares;
    int permutationID;

    public TriangleSegmentView(Context context) {
        super(context);
        squares = new LinkedList<>();
        permutationID = 0;
        createRectangles();
    }

    public TriangleSegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        squares = new LinkedList<>();
        createRectangles();
    }

    public void createRectangles(){
        int width = Utils.getScreenWidth(getContext())/9;
        int height = Utils.getScreenHeight(getContext())/9;
        if(height < width)
            width = height;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4 - i; k++) {
                int beg = width /2 * i;
                int cor = k*width/4  + width/6 *i;
                Square square = new Square(new Rect(i * width + 1 , beg + k * width +1 + cor, (i + 1) * width -1 , beg +(k + 1) * width-1 + cor));
                squares.add(square);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        for( Square r : squares)
            canvas.drawRect(r.getRect(), r.getPaint());
    }

    public int getPermutationID() {
        return permutationID;
    }

    public void setPermutationID(int permutationID) {
        this.permutationID = permutationID;
    }

    public void addToPermutationID(int i){
        int id = (getPermutationID() + i)%3;
        if(id <0)
            id = 2;
        setPermutationID(id);
    }
}