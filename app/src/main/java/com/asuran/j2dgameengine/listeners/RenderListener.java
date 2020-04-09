package com.asuran.j2dgameengine.listeners;

import javax.microedition.khronos.opengles.GL10;

public interface RenderListener {
    void onDisplayChanged(float w, float x, float h, float y);
    void onFramePerSecond(int fps);
    void onFrameTime(float frameTime);
    void onBegin(GL10 gl);
    void onSetup();
    void onUpdate();
    void onDraw();
}
