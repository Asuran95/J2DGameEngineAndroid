package com.asuran.j2dgameengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.asuran.j2dgameengine.utils.GLRect;
import com.asuran.j2dgameengine.utils.GLSprite;
import com.asuran.j2dgameengine.utils.GLVec2f;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class J2DCanvas {

    private GL10 gl;
    private GLVec2f display;
    private int idTexHitbox;

    public J2DCanvas(GL10 gl, GLVec2f display, Context context){
        this.gl = gl;
        this.display = display;
        loadHitboxTex(context);
    }

    private void loadHitboxTex(Context context){

        Bitmap image = null;
        try {
            image = BitmapFactory.decodeStream(context.getAssets().open("rectbox.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] textureCoords = {
                0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f
        };

        FloatBuffer mTextureBuffer;

        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);

        int[] textid = new int[1];

        gl.glGenTextures(1 , textid, 0); // 2
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textid[0]); // 3

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, image, 0); // 4

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR); // 5a
        //gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR); // 5b

        idTexHitbox = textid[0];
    }

    public void drawSprite(GLSprite glSprite, float x, float y){

        float calcX = display.v0*3*(-1) + 3*x*2;
        float calcY = 3 - 3*y*2;

        //gl.glPushMatrix();

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();

        //gl.glTranslatef(-3.0f,0.0f, -3.0f);

        gl.glTranslatef(calcX,calcY, -3.0f);

        gl.glRotatef(glSprite.getAngle(),0.0f,0.0f,1.0f);

        //GL11 gl11 = (GL11) gl;
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, glSprite.getVertexBuffer());
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, mColorBuffer);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D); 				//1
        gl.glEnable(GL10.GL_BLEND);					//2

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);			//3
        gl.glBindTexture(GL10.GL_TEXTURE_2D, glSprite.getTextureId());		//4

        gl.glTexCoordPointer(2, GL10.GL_FLOAT,0, glSprite.getTextureBuffer());	//5
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//6

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);			//7

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//8

        //gl.glPopMatrix();
    }

    public void drawSpritePosition(GLSprite glSprite, float x, float y){

        float calcX = display.v0*3*(-1) + display.v0*3*x*2;
        float calcY = 3 - 3*y*2;

        gl.glPushMatrix();

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();

        //gl.glTranslatef(-3.0f,0.0f, -3.0f);

        gl.glTranslatef(calcX,calcY, -3.0f);

        gl.glRotatef(glSprite.getAngle(),0.0f,0.0f,1.0f);

        //GL11 gl11 = (GL11) gl;
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, glSprite.getVertexBuffer());
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, mColorBuffer);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D); 				//1
        gl.glEnable(GL10.GL_BLEND);					//2

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);			//3
        gl.glBindTexture(GL10.GL_TEXTURE_2D, glSprite.getTextureId());		//4

        gl.glTexCoordPointer(2, GL10.GL_FLOAT,0, glSprite.getTextureBuffer());	//5
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//6

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);			//7

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//8

        gl.glPopMatrix();
    }

    public void setBackgroundColor(float red, float green, float blue, float alpha){
        gl.glClearColor(red, green, blue, alpha);
    }

    public void drawRect(GLRect glRect){
        float calcX = display.v0*3*(-1) + 3*glRect.getW()*2;
        float calcY = 3 - 3*glRect.getH()*2;

        //gl.glPushMatrix();

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();

        //gl.glTranslatef(-3.0f,0.0f, -3.0f);

        gl.glTranslatef(calcX,calcY, -3.0f);

        //gl.glRotatef(glSprite.getAngle(),0.0f,0.0f,1.0f);

        //GL11 gl11 = (GL11) gl;
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, glRect.getVertexBuffer());
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, mColorBuffer);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D); 				//1
        gl.glEnable(GL10.GL_BLEND);					//2

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);			//3
        gl.glBindTexture(GL10.GL_TEXTURE_2D, idTexHitbox);		//4

        gl.glTexCoordPointer(2, GL10.GL_FLOAT,0, glRect.getTextureBuffer());	//5
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//6

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);			//7

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		//8

        //gl.glPopMatrix();
    }
}
