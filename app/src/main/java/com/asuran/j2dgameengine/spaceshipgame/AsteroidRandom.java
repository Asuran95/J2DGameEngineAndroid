package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;

import java.util.ArrayList;
import java.util.Random;

public class AsteroidRandom extends AbstractEntity {
    public AsteroidRandom(GameEngine manager) {
        super(manager);
    }

    private Random rand = new Random();
    private ArrayList<Asteroid> asteroids = new ArrayList();

    long timeElapse = System.currentTimeMillis();
    @Override
    public void update() {
        ArrayList<Asteroid> asteroidsAlive = new ArrayList();
        for (Asteroid a : asteroids) {
            if(a.isAlive()){
                asteroidsAlive.add(a);
            }
        }
        asteroids = asteroidsAlive;

        if(System.currentTimeMillis() > timeElapse+500){
            asteroids.add(new Asteroid(manager, rand.nextFloat()*(manager.getWidth() - 0.1f) + 0.1f, rand.nextFloat()*(0.1f - 0.05f) + 0.05f, rand.nextFloat()*(0.5f - 0.1f) + 0.1f));
            timeElapse = System.currentTimeMillis();
        }
    }
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }
}
