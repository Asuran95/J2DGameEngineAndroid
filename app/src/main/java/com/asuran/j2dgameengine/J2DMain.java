package com.asuran.j2dgameengine;

import com.asuran.j2dgameengine.spaceshipgame.SpaceShipMain;

public class J2DMain {

    public void loadTextures(SpriteLoader spriteLoader){
        spriteLoader.addTexture("background.png");
        spriteLoader.addTexture("starsoneb.png");
        spriteLoader.addTexture("ship.png");
        spriteLoader.addTexture("explosionb.png", 4, 4);
        spriteLoader.addTexture("asteroidb.png");
        spriteLoader.addTexture("shot.png");
        spriteLoader.addTexture("alphabet.png", 17, 3);
    }

    public void setupEntitiesGame(GameEngine gameEngine){
        gameEngine.addEntity(new SpaceShipMain());
    }
}
