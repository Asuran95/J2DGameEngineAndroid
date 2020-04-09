package com.asuran.j2dgameengine.spaceshipgame;


import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.listeners.GestureListener;
import com.asuran.j2dgameengine.utils.GLRect;

public class SpaceShipMain extends AbstractEntity {
    public SpaceShipMain(GameEngine manager) {
        super(manager);
    }

    private SpaceBackground spaceBackground;
    private PlayerShip playerShip;
    private GLRect pointer = new GLRect(0.1f, 0.1f, 0.0f, 0.0f);

    @Override
    public void setup(SpriteLoader spriteLoader) {


        spaceBackground = new SpaceBackground(this.manager);
        playerShip = new PlayerShip(this.manager);
        AsteroidRandom asteroidRandom = new AsteroidRandom(this.manager);
        new CollisionCore(manager, playerShip, asteroidRandom);
        manager.addTouchListener(new Touch());

    }

    @Override
    public void draw(J2DCanvas j2d) {
        //j2d.drawRect(pointer);
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
