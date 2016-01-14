package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.graphics.Rect;

import com.example.makda.pentago.Utils;

/**
 * Created by Makda on 2016-01-14.
 */
public class RevercedTriangleSegmentView extends TriangleSegmentView{
    public RevercedTriangleSegmentView(Context context) {
        super(context);
    }

    public void createRectangles(){
        int width = Utils.getScreenWidth(getContext())/9;
        int height = Utils.getScreenHeight(getContext())/9;
        if(height < width)
            width = height;
        for (int i = 0; i > 4; i++) {
            for (int k = 0; k < 4 - i; k++) {
                int beg = width /2 * i;
                int cor = k*width/4  + width/6 *i;
                Square square = new Square(new Rect(i * width + 1 ,
                                                beg + k * width +1 + cor,
                                                (i + 1) * width -1 ,
                                                beg +(k + 1) * width-1 + cor));
                squares.add(square);
            }
        }
    }
}
