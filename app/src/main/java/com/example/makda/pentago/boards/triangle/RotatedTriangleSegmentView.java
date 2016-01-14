package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.graphics.Rect;

import com.example.makda.pentago.Utils;

/**
 * Created by Makda on 2016-01-14.
 */
public class RotatedTriangleSegmentView extends TriangleSegmentView {
    public RotatedTriangleSegmentView(Context context) {
        super(context);
        createPermutation();
    }

    public void createRectangles(){
        int width = Utils.getScreenWidth(getContext())/9;
        int height = Utils.getScreenHeight(getContext())/9;
        if(height < width)
            width = height;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 1 + i; k++) {
                int beg = width /2 * (3-i);
                int cor = k*width/4  + width/6 *(4-i);
                Square square = new Square(new Rect(i * width + 1 +30, beg+k * width +1 + cor -20, (i + 1) * width -1 +30, beg+(k + 1) * width-1 + cor-20));
                squares.add(square);
            }
        }
    }

    private void createPermutation(){
        permutations = new int[][]{
                {0,1,2,3,4,5,6,7,8,9},
                {9,5,8,2,4,3,0,1,7,6},
                {6,3,7,8,4,1,9,5,2,0}};
    }
}
