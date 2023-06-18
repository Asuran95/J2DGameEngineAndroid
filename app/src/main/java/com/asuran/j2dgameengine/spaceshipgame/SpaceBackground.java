package com.asuran.j2dgameengine.spaceshipgame;

import com.asuran.j2dgameengine.GameEngine;
import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.utils.GLSprite;

public class SpaceBackground extends AbstractEntity {
    private GLSprite background;
    private GLSprite star1;
    private GLSprite star2;
    private GLSprite star3;
    private float speed = 0.03f;
    private float deslocA = 0.0f;
    private float deslocB = 0.0f;
    private float deslocC = 0.0f;
    private float deslocD = 0.0f;

    @Override
    public void setup(SpriteLoader spriteLoader) {
        background = spriteLoader.getTexture("background.png");
        star1 = spriteLoader.getTexture("starsoneb.png");
        star1.resize(0.2f);
        star2 = spriteLoader.getTexture("starsoneb.png");
        star2.resize(0.3f);
        star3 = spriteLoader.getTexture("starsoneb.png");
        star3.resize(0.4f);
        background.resize(2);
    }

    @Override
    public void update() {
        //System.out.println(j2d.getLastTickTime());
        float deslocCalc = speed * getGameEngine().getLastFrameTime();
        //float deslocCalc = speed;

        deslocA += deslocCalc;

        if(deslocA>=background.getSizes().v1){
            deslocA = 0.0f;
        }

        deslocB += deslocCalc*1.5f;

        if(deslocB>=star1.getSizes().v1){
            deslocB = 0.0f;
        }

        deslocC += deslocCalc*2;

        if(deslocC>=star2.getSizes().v1){
            deslocC = 0.0f;
        }

        deslocD += deslocCalc*2.5f;

        if(deslocD>=star3.getSizes().v1){
            deslocD = 0.0f;
        }


    }

    @Override
    public void draw(J2DCanvas j2d) {
        j2d.setBackgroundColor(0.0f, 0.0f, 0.0f,0.0f);

        j2d.drawSprite(background, getGameEngine().getWidth()/2,(background.getSizes().v1/2*-1)+1 + deslocA);
        j2d.drawSprite(background, getGameEngine().getWidth()/2,(background.getSizes().v1/2*-3)+1 + deslocA);

        for(int i=0; i<4; i++){
            for(int a=-1; a<8; a++){
                j2d.drawSprite(star1, (i*star1.getSizes().v0)+star1.getSizes().v0/2,(a*star1.getSizes().v1)+(star1.getSizes().v1/2)+deslocB);
            }
        }

        for(int i=0; i<2; i++){
            for(int a=-1; a<4; a++){
                j2d.drawSprite(star2, (i*star2.getSizes().v0)+star2.getSizes().v0/2,(a*star2.getSizes().v1)+(star2.getSizes().v1/2)+deslocC);
            }
        }

        for(int i=0; i<2; i++){
            for(int a=-1; a<3; a++){
                j2d.drawSprite(star3, (i*star3.getSizes().v0)+star3.getSizes().v0/2,(a*star3.getSizes().v1)+(star3.getSizes().v1/2)+deslocD);
            }
        }
    }
}
