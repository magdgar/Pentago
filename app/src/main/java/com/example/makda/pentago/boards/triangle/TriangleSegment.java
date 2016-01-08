package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.makda.pentago.boards.square.QuarterBoard;

public class TriangleSegment extends ViewGroup {
    TriangleView triangles[];

    public TriangleSegment(Context context) {
        super(context);
        createTrianglesViews();
    }

    private void createTrianglesViews(){
        triangles = new TriangleView[9];
        for(int i =0; i<9; i++)
            triangles[i] = new TriangleView(getContext());
        for(TriangleView triangle : triangles)
            addView(triangle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        triangles[0].layout(l, t, r, b);
    }
}
