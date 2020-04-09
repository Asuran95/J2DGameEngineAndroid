package com.example.j2dframeworkandroid.spaceshipgame;

import com.example.j2dframeworkandroid.GameEngine;
import com.example.j2dframeworkandroid.J2DCanvas;
import com.example.j2dframeworkandroid.SpriteLoader;
import com.example.j2dframeworkandroid.entitiesclasses.AbstractEntity;
import com.example.j2dframeworkandroid.utils.GLRect;
import com.example.j2dframeworkandroid.utils.GLSprite;

import java.util.ArrayList;

public class PlayerShip extends AbstractEntity {

    public PlayerShip(GameEngine manager) {
        super(manager);
    }

    GLSprite shipSprite;

    private float posX = manager.getWidth() * 0.5f;
    private float h = 0.8f;
    private GLRect hitboxa;
    private GLRect hitboxb;
    private float dHitbox = 0.04f;
    private GLRect touchHitbox;
    private ArrayList<LaserShot> shots = new ArrayList<>();
    private boolean fire = false;

    @Override
    public void setup(SpriteLoader spriteLoader) {
        shipSprite = spriteLoader.getTexture("ship.png");
        shipSprite.resize(0.2f);
        hitboxa = new GLRect(shipSprite.getWidht()-(shipSprite.getWidht()*0.8f), shipSprite.getHeight()-(shipSprite.getHeight()*0.5f), posX, h);
        hitboxb = new GLRect(shipSprite.getWidht()-(shipSprite.getWidht()*0.4f), shipSprite.getHeight()-(shipSprite.getHeight()*0.85f), posX, (h+dHitbox));
        touchHitbox = new GLRect(shipSprite.getWidht(), shipSprite.getHeight(), posX, h);
    }

    @Override
    public void update() {
        hitboxa.setPos(posX, h);
        hitboxb.setPos(posX, (h+dHitbox));
        touchHitbox.setPos(posX, h);


        ArrayList<LaserShot> shotsAlive = new ArrayList();
        for(LaserShot a : shots){
            if(a.isAlive()){
                shotsAlive.add(a);
            }
        }

        shots = shotsAlive;

        if(fire){
            fire();
        }

    }

    @Override
    public void draw(J2DCanvas j2d) {
        j2d.drawSprite(shipSprite, posX, h);
        //j2d.drawRect(hitboxa);
        //j2d.drawRect(hitboxb);
    }

    public GLRect getHitbox(){
        //return new Rect(left,top,right,bottom);
        return hitboxa;
    }

    public boolean isHitMe(GLRect glRect){

        if(hitboxa.intersect(glRect)){
            return true;
        }

        if(hitboxb.intersect(glRect)){
            return true;
        }

        return false;
    }

    public GLRect getTouchHitbox() {
        return touchHitbox;
    }

    public void fireAndMove(float posX){
        this.posX = posX;
    }

    private long timeElapse = System.currentTimeMillis();
    public void fire(){


        if(System.currentTimeMillis() > timeElapse+300){
            shots.add(new LaserShot(manager, posX));
            timeElapse = System.currentTimeMillis();
        }
    }

    public void startFire(){
        fire = true;
    }

    public void stopFire(){
        fire = false;
    }


    public ArrayList<LaserShot> getShots() {
        return shots;
    }
}
