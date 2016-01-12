package com.example.makda.pentago.boards.triangle;

import android.graphics.Rect;

/**
 * Created by Makda on 2016-01-12.
 */
public class Square extends com.example.makda.pentago.boards.square.Square{
    public Square(Rect rect) {
        super(rect);
    }
    public void setColor(int color){
        this.getPaint().setColor(color);
        if(color == -7829368)
            this.setIsSelected(false);
    }
}
