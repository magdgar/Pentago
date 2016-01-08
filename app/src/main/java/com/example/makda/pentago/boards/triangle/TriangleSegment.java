package com.example.makda.pentago.boards.triangle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.makda.pentago.boards.square.QuarterBoard;

import static android.R.color.holo_red_dark;

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
        triangles[0].layout(l, t, r/3, b/3);
        triangles[1].layout(r / 3, t, 2* r / 3, b/3);
        triangles[2].layout(2 * r / 3, t, r, b / 3);

        triangles[3].layout(r / 6, t - 110, r / 2, b / 3 - 110);
        triangles[3].setRotation(180);
        triangles[4].layout(r / 2, t - 110, 5 * r / 6, b / 3 - 110);
        triangles[4].setRotation(180);


        triangles[5].layout(r / 6, b / 3 - 108, r / 2, 2 * b / 3 - 108);
        triangles[6].layout(r / 2, b/3 -108, 5 * r / 6, 2*b/3 -108);

        triangles[7].layout(r / 3,  b / 3 -216, 2* r / 3,2*b/3 -216);
        triangles[7].setRotation(180);

        triangles[8].layout(r / 3, 2 * b / 3 - 213, 2 * r / 3, b);
    }
}
