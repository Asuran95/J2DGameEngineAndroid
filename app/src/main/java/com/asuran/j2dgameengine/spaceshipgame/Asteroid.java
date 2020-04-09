package com.example.j2dframeworkandroid.spaceshipgame;

import com.example.j2dframeworkandroid.GameEngine;
import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.entitiesclasses.AbstractEntity;
import com.example.j2dframeworkandroid.utils.GLRect;
import com.example.j2dframeworkandroid.utils.GLSprite;
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

    public Asteroid(GameEngine manager, float posX, float size, float speed) {
        super(manager);
        this.posX = posX;
        this.size = size;
        this.speed = speed;
        this.direction = new Random().nextInt(2);
        hitbox = new GLRect(0, 0, 0, 0);
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        asteroid = spriteLoader.getTexture("asteroidb.png");
        asteroid.resize(size);
        init = true;
        hitbox = new GLRect(asteroid.getWidht(), asteroid.getHeight(), posX, h);
    }

    @Override
    public void update() {
        h += speed*manager.getLastFrameTime();


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
        new Explosion(this.manager, posX, h, size, speed);
        alive = false;
    }

    public GLRect getHitbox(){
        //return new Rect(50,100,100,50);

        return hitbox;

    }
}
