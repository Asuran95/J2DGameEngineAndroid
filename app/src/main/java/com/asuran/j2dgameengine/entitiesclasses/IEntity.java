package com.asuran.j2dgameengine.entitiesclasses;

import com.asuran.j2dgameengine.J2DCanvas;
import com.asuran.j2dgameengine.SpriteLoader;
import com.asuran.j2dgameengine.utils.GLVec2f;

public interface IEntity {
    public void setup(SpriteLoader spriteLoader);
    public void update();
    public void draw(J2DCanvas j2d);
    public int getLayer();
    public GLVec2f getPosition();
    public boolean isAlive();

}
