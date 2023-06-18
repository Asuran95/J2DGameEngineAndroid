package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.utils.GLRect;
import com.asuran.j2dgameengine.utils.GLSprite;

public class LaserShot extends AbstractEntity {

    public float w;
    public float h = 0.72f;
    public float speed = 1.8f;
    private GLSprite shot;
    private GLRect hitbox;

    public LaserShot(float w) {
        this.w = w;
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        shot = spriteLoader.getTexture("shot.png");
        shot.resize(0.06f);
        hitbox = new GLRect(shot.getWidht(), shot.getHeight(), w, h);
        hitbox.setPos(w, h);
    }

    @Override
    public void update() {
        hitbox.setPos(w, h);
        h -= speed * getGameEngine().getLastFrameTime();

        if(h<0) {
            alive = false;
        }
    }

    @Override
    public void draw(J2DCanvas j2d) {
        j2d.drawSprite(shot, w, h);
    }

    public GLRect getHitbox(){
        //return new Rect(50,100,100,50);
        return hitbox;
    }

    public void destroy(){
        alive = false;
    }
}
