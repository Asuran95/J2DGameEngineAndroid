package com.example.j2dframeworkandroid.entitiesclasses;

import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.utils.GLVec2f;

public interface IEntity {
    public void setup(SpriteLoader spriteLoader);
    public void update();
    public void draw(J2DCanvas j2d);
    public int getLayer();
    public GLVec2f getPosition();
    public boolean isAlive();

}
