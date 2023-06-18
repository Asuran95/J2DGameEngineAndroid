package com.asuran.j2dgameengine.spaceshipgame;


import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.listeners.GestureListener;
import com.asuran.j2dgameengine.utils.GLRect;

public class SpaceShipMain extends AbstractEntity {
    private SpaceBackground spaceBackground;
    private PlayerShip playerShip;
    private GLRect pointer = new GLRect(0.1f, 0.1f, 0.0f, 0.0f);

    @Override
    public void setup(SpriteLoader spriteLoader) {
        spaceBackground = new SpaceBackground();
        getGameEngine().addEntity(spaceBackground);

        playerShip = new PlayerShip();
        getGameEngine().addEntity(playerShip);

        AsteroidRandom asteroidRandom = new AsteroidRandom();
        getGameEngine().addEntity(asteroidRandom);

        getGameEngine().addEntity(new CollisionCore(playerShip, asteroidRandom));
        getGameEngine().addTouchListener(new Touch());
    }

    class Touch implements GestureListener{

        private boolean moveShip = false;
        @Override
        public void onDown(float x, float y) {
            pointer.setPos(x, y);
            if(pointer.intersect(playerShip.getHitbox())){
                moveShip = true;
                playerShip.startFire();
            }
        }

        @Override
        public void onUp(float x, float y) {
            pointer.setPos(0.0f, 0.0f);
            moveShip = false;
            playerShip.stopFire();
        }

        @Override
        public void onMoving(float x, float y) {
            if(moveShip){
                playerShip.fireAndMove(x);
            }
        }
    }
}
