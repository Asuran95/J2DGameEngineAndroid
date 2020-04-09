package com.example.j2dframeworkandroid;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.example.j2dframeworkandroid.listeners.TouchListener;

public class RenderView extends GLSurfaceView {

    GameEngine manager;
    TouchListener touchListener;

    public RenderView(Context context) {
        super(context);

        RendererGL renderer = new RendererGL();

        manager = new GameEngine(context, renderer, this);

        setRenderer(renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(touchListener != null){
            touchListener.onTouch(event, event.getX(), event.getY());
        }

        return true;
    }

    public void setTouchListener(TouchListener touchListener){
        this.touchListener = touchListener;
    }




}
