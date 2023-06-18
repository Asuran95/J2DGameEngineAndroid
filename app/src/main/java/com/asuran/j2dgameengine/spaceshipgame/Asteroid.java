package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.utils.GLRect;
import com.asuran.j2dgameengine.utils.GLSprite;
import java.util.Random;


public class Asteroid extends AbstractEntity {

    private GLSprite asteroid;
    private float speed = 0.3f;
    private float h = -0.1f;
    private float posX;
    private float size;
    private boolean init = false;
    private float angle = 1;
    private int direction;
    private GLRect hitbox;

    public Asteroid(float posX, float size, float speed) {
        this.posX = posX;
        this.size = size;
        this.speed = speed;
        this.direction = new Random().nextInt(2);
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        asteroid = spriteLoader.getTexture("asteroidb.png");
        asteroid.resize(size);
        init = true;
        hitbox = new GLRect(asteroid.getWidht(), asteroid.getHeight(), posX, h);
        hitbox.setPos(posX, h);
    }

    @Override
    public void update() {
        h += speed * getGameEngine().getLastFrameTime();

        if(direction == 0){
            angle += (0.1f * (speed*10));
        } else {
            angle -= (0.1f * (speed*10));
        }

        asteroid.setAngle(angle);
        if(h > 0.5f){
            //explode();
        }

        if(h > 1.1f){
            alive = false;
        }

        hitbox.setPos(posX, h);
    }

    @Override
    public void draw(J2DCanvas j2d) {
        j2d.drawSprite(asteroid, posX, h);
        //j2d.drawRect(hitbox);
    }

    public void explode(){
        getGameEngine().addEntity(new Explosion(posX, h, size, speed));

        alive = false;
    }

    public GLRect getHitbox(){
        //return new Rect(50,100,100,50);

        return hitbox;

    }
}
