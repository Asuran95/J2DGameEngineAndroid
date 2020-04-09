package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;

import java.util.ArrayList;

public class CollisionCore extends AbstractEntity {

    private PlayerShip playerShip;
    private AsteroidRandom asteroidRandom;
    private ComboText comboText;

    public CollisionCore(GameEngine manager, PlayerShip playerShip, AsteroidRandom asteroidRandom) {
        super(manager);
        this.playerShip = playerShip;
        this.asteroidRandom = asteroidRandom;
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        comboText = new ComboText(manager);
    }

    @Override
    public void update() {

        ArrayList<Asteroid> asteroids = asteroidRandom.getAsteroids();
        ArrayList<LaserShot> shots = playerShip.getShots();

        Thread t = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<asteroids.size(); i++){

                    if(playerShip.isHitMe(asteroids.get(i).getHitbox())){
                        comboText.resetCounter();
                        asteroids.get(i).explode();
                    }
                }
            }
        };
        t.start();

        for(int i=0; i<asteroids.size(); i++){

            for(int a = 0; a<shots.size(); a++){
                if(asteroids.get(i).getHitbox().intersect(shots.get(a).getHitbox())){

                    if(shots.get(a).isAlive()){
                        comboText.incrementCounter();
                        asteroids.get(i).explode();
                        shots.get(a).destroy();
                    }
                }
            }
        }

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
