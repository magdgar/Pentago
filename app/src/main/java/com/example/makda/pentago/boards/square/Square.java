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

    public void setColor(int color){
        paint.setColor(color);
    }
}