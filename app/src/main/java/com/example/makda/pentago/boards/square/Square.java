package com.example.makda.pentago.boards.square;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square {

    Rect rect;
    Paint paint;
    boolean isSelected;

    public Square(Rect rect){
        this.rect = rect;
        paint = new Paint();
        paint.setColor(Color.GRAY);
        isSelected = false;
    }

    public boolean contains(int x, int y){
        if(x > rect.left && x < rect.right)
            if (y > rect.top && y < rect.bottom)
                return true;
        return false;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setColor(int color){
        paint.setColor(color);
    }
}