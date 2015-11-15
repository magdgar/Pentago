package com.example.makda.pentago;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Rectangle {
    Rect rect;
    Paint paint;

    public Rectangle(Rect rect){
        this.rect = rect;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public boolean contains(int x, int y){
        if(x > rect.left && x < rect.right)
            if(y > rect.top && y < rect.bottom)
                return true;
        return false;
    }

    public Paint getPaint() {
        return paint;
    }

    public Rect getRect() {
        return rect;
    }
}