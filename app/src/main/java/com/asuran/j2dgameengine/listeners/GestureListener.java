package com.asuran.j2dgameengine.listeners;

public interface GestureListener {

    void onDown(float x, float y);
    void onUp(float x, float y);
    void onMoving(float x, float y);
}
