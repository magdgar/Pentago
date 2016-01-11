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
        int width = Utils.getScreenWidth(getContext())/11;
        int height = Utils.getScreenHeight(getContext())/11;
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
        canvas.drawColor(Color.WHITE);
        for( Square r : squares)
            canvas.drawRect(r.getRect(), r.getPaint());
    }

    public int getPermutationID(){
        //TODO if rotation triangle is not moveing we only reads colors and repaint
        int r = ((int)getRotation() )% 360;
        if( r == 0)
            return 0;
        if(r == 120 || r == -240)
            return 2;
        if(r == 240 || r == -120)
            return 1;
        return 0;
    }
}