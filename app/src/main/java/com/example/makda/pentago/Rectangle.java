package com.example.makda.pentago;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Rectangle extends View{

    Rect rect;
    private Paint paint;
    boolean isSelected;

    public Rectangle(Context context, Rect rect){
        super(context);
        this.rect = rect;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        isSelected = false;
    }

    public void draw(Canvas canvas){
        canvas.drawRect(this.rect, paint);
    }

    public boolean contains(int x, int y){
        if(x > rect.left && x < rect.right)
            if(y > rect.top && y < rect.bottom)
                return true;
        return false;
    }

    public void setColor(int color){
        paint.setColor(color);
    }
}