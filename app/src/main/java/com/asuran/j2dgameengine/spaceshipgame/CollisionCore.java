package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;

import java.util.ArrayList;

public class CollisionCore extends AbstractEntity {

    private PlayerShip playerShip;
    private AsteroidRandom asteroidRandom;
    private ComboText comboText;

    public CollisionCore(PlayerShip playerShip, AsteroidRandom asteroidRandom) {
        this.playerShip = playerShip;
        this.asteroidRandom = asteroidRandom;
    }

    @Override
    public void setup(SpriteLoader spriteLoader) {
        comboText = new ComboText();
        getGameEngine().addEntity(comboText);
    }

    @Override
    public void update() {

        ArrayList<Asteroid> asteroids = asteroidRandom.getAsteroids();
        ArrayList<LaserShot> shots = playerShip.getShots();

        for (Asteroid asteroid : asteroids) {
            if (asteroid.getHitbox() == null){
                continue;
            }

            for (LaserShot laserShot : shots) {

                if (laserShot.getHitbox() == null){
                    continue;
                }

                if(asteroid.getHitbox().intersect(laserShot.getHitbox())){
                    if(laserShot.isAlive()) {
                        comboText.incrementCounter();
                        asteroid.explode();
                        laserShot.destroy();
                    }
                }
            }

            if (playerShip.isHitMe(asteroid.getHitbox())) {
                comboText.resetCounter();
                asteroid.explode();
            }
        }
    }
}
