package com.example.j2dframeworkandroid.spaceshipgame;

import com.example.j2dframeworkandroid.GameEngine;
import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.entitiesclasses.AbstractEntity;
import com.example.j2dframeworkandroid.utils.GLSprite;

public class Explosion extends AbstractEntity {

    private GLSprite explosion;
    private float x;
    private float y;
    private float size;
    private float speed;

    public Explosion(GameEngine manager, float x, float y, float size, float speed) {
        super(manager);
        this.x = x;
        this.y = y;
        this.size = size * 2;
        this.speed = speed;
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        explosion = spriteLoader.getTexture("explosionb.png");

        explosion.resize(size);
    }

    long timeElapse = System.currentTimeMillis();

    @Override
    public void update() {
        y += speed*manager.getLastFrameTime();

        if(System.currentTimeMillis() > timeElapse+64){

            if(explosion.nextSprite()){
                alive = false;
            }
            timeElapse = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(J2DCanvas j2d) {
        j2d.drawSprite(explosion, x, y);
    }
}
