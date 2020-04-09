package com.asuran.j2dgameengine.entitiesclasses;

import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.utils.GLVec2f;

public interface IEntity {
    void setup(SpriteLoader spriteLoader);
    void update();
    void draw(J2DCanvas j2d);
    int getLayer();
    GLVec2f getPosition();
    boolean isAlive();

}
