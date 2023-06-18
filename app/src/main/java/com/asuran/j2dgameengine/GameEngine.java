package com.asuran.j2dgameengine;

import android.content.Context;

import com.asuran.j2dgameengine.entitiesclasses.AbstractEntity;
import com.asuran.j2dgameengine.listeners.GestureListener;
import com.asuran.j2dgameengine.listeners.RenderListener;
import com.asuran.j2dgameengine.utils.GLVec2f;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class GameEngine {

    private J2DCanvas j2d;
    private ArrayList<AbstractEntity> entities = new ArrayList<>();
    private ArrayList<AbstractEntity> newEntities = new ArrayList<>();
    private GLVec2f display = new GLVec2f();
    private int framesPerSecond;
    private float lastFrameTime;
    private SpriteLoader spriteLoader;
    private Context context;
    private RenderView renderView;
    private float realX;
    private float realY;
    private ArrayList<GestureListener> gestureListeners = new ArrayList<>();

    public GameEngine(Context context, RendererGL rendererGL, RenderView renderView){
        this.context = context;
        rendererGL.setRenderListener(new RenderListenerImp(this));
        this.renderView = renderView;
    }

    public void addEntity(AbstractEntity entity) {
        entity.setGameEngine(this);
        newEntities.add(entity);
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public float getLastFrameTime() {
        return lastFrameTime;
    }

    public float getWidth() {
        return display.v0;
    }

    public float getHeight() {
        return display.v1;
    }

    public void addTouchListener(GestureListener listener){
        gestureListeners.add(listener);
    }

    class RenderListenerImp implements RenderListener {

        private GameEngine gameEngine;

        public RenderListenerImp(GameEngine gameEngine) {
            this.gameEngine = gameEngine;
        }

        @Override
        public void onDisplayChanged(float w, float x, float h, float y) {
            display.v0 = w;
            display.v1 = h;
            realX = x;
            realY = y;
        }

        @Override
        public void onFramePerSecond(int fps) {
            framesPerSecond = fps;
        }

        @Override
        public void onFrameTime(float frameTime) {
            lastFrameTime = frameTime;
        }

        @Override
        public void onBegin(GL10 gl) {
            j2d = new J2DCanvas(gl, display, context);
            spriteLoader = new SpriteLoader(gl, context);
            J2DMain main = new J2DMain();
            main.loadTextures(spriteLoader);
            main.setupEntitiesGame(gameEngine);

            renderView.setTouchListener((event, x, y) -> {
                float posY = y/realY;
                float posX = (x/realX)*display.v0;

                if(event.getAction() == 0){
                    for(GestureListener g : gestureListeners){
                        g.onDown(posX, posY);
                    }

                } else if(event.getAction() == 1){
                    for(GestureListener g : gestureListeners){
                        g.onUp(posX, posY);
                    }
                } else {
                    for(GestureListener g : gestureListeners){
                        g.onMoving(posX, posY);
                    }
                }
            });
        }

        @Override
        public void onSetup() {
            for(int i=0; i<newEntities.size(); i++){
                newEntities.get(i).setup(spriteLoader);
                entities.add(newEntities.get(i));
            }
            newEntities = new ArrayList<>();
        }

        @Override
        public void onUpdate() {
            ArrayList<AbstractEntity> entitiesUpdated = new ArrayList<>();

            for(int i=0; i<entities.size(); i++){
                entities.get(i).update();

                if(entities.get(i).isAlive()){
                    entitiesUpdated.add(entities.get(i));
                } else {
                    System.out.println("Entity deleted: " + entities.get(i));
                }
            }

            entities = entitiesUpdated;
        }

        @Override
        public void onDraw() {
            for(int i=0; i<entities.size(); i++){
                entities.get(i).draw(j2d);
            }
        }
    }
}
