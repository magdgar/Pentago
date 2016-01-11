package com.example.makda.pentago.boards.triangle.triangle.openGL;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.makda.pentago.boards.triangle.triangle.openGL.MyGLRenderer;

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}