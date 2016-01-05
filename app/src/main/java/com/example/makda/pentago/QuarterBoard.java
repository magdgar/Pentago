package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;


public class QuarterBoard extends View {
    LinkedList<Rectangle> rectangles;

    public QuarterBoard(Context context) {
        super(context);
        rectangles = new LinkedList<>();
        createRectangles();
    }

    public QuarterBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectangles = new LinkedList<>();
        createRectangles();
    }

    public void createRectangles(){
        int width = Utils.getWidth(getContext());
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                Rectangle rectangle = new Rectangle(new Rect(i * (width)/ 3 +2, k * (width)/ 3 +2, (i + 1) * (width) / 3-2, (k + 1) * (width)/ 3-2));
                rectangles.add(rectangle);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(/*new Random().nextInt()*/ Color.WHITE);
        for( Rectangle r : rectangles)
            canvas.drawRect(r.rect, r.paint);
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