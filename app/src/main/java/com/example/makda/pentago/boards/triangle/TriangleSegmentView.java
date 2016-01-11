package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.makda.pentago.Utils;
import com.example.makda.pentago.boards.square.Square;

import java.util.LinkedList;


public class TriangleSegmentView extends View {
    LinkedList<Square> squares;

    public TriangleSegmentView(Context context) {
        super(context);
        squares = new LinkedList<>();
        createRectangles();
    }

    public TriangleSegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        squares = new LinkedList<>();
        createRectangles();
    }

    public void createRectangles(){
        int width = Utils.getScreenWidth(getContext())/10;
        int height = Utils.getScreenHeight(getContext())/10;
        if(height < width)
            width = height;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 3; k++) {
                Square square = new Square(new Rect(i * width +1 , k * width +1, (i + 1) * width -1 , (k + 1) * width-1));
                squares.add(square);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        for( Square r : squares)
            canvas.drawRect(r.getRect(), r.getPaint());
    }

    public int getPermutationID(){
        int r = ((int)getRotation() )% 360;
        if( r == 0)
            return 0;
        if(r == 90 || r == -270)
            return 3;
        if(r == 180 || r == -180)
            return 2;
        if(r == 270 || r == -90)
            return 1;
        return 0;
    }
}