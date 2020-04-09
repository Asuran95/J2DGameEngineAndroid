package com.example.j2dframeworkandroid.spaceshipgame;

import com.example.j2dframeworkandroid.GameEngine;
import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.entitiesclasses.AbstractEntity;
import com.example.j2dframeworkandroid.utils.GLRect;
import com.example.j2dframeworkandroid.utils.GLSprite;

public class LaserShot extends AbstractEntity {

    public float w;
    public float h = 0.72f;
    public float speed = 1.8f;
    private GLSprite shot;
    private GLRect hitbox;

    public LaserShot(GameEngine manager, float w) {
        super(manager);
        this.w = w;
        hitbox = new GLRect(0, 0, 0, 0);
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        shot = spriteLoader.getTexture("shot.png");
        shot.resize(0.06f);
        hitbox = new GLRect(shot.getWidht(), shot.getHeight(), w, h);
    }

    @Override
    public void update() {
        hitbox.setPos(w, h);
        h -= speed*manager.getLastFrameTime();

        if(h<0){
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
