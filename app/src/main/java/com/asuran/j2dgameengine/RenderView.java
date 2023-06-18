package com.asuran.j2dgameengine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.asuran.j2dgameengine.listeners.TouchListener;

public class RenderView extends GLSurfaceView {

    private TouchListener touchListener;

    public RenderView(Context context) {
        super(context);
        RendererGL renderer = new RendererGL();
        new GameEngine(context, renderer, this);
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
