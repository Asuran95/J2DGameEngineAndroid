package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.utils.GLSprite;

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
