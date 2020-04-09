package com.example.j2dframeworkandroid.entitiesclasses;

import com.example.j2dframeworkandroid.GameEngine;
import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.utils.GLVec2f;

public abstract class AbstractEntity implements IEntity {

    protected boolean alive = true;
    protected int layer = 0;
    protected GLVec2f position = new GLVec2f(0, 0);
    protected GameEngine manager;

    public AbstractEntity(GameEngine manager){
        manager.addEntity(this);
        this.manager = manager;
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(J2DCanvas j2d) {

    }

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public GLVec2f getPosition() {
        return position;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }


}
