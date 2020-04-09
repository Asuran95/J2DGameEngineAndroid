package com.asuran.j2dgameengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.asuran.j2dgameengine.utils.GLSprite;
import com.asuran.j2dgameengine.utils.GLVec2f;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class SpriteLoader {

    private GL10 gl;
    private Context context;
    private HashMap<String, GLSprite> texturePool = new HashMap<>();

    public SpriteLoader(GL10 gl, Context context) {
        this.gl = gl;
        this.context = context;

    }

    public void addTexture(String file){

        Bitmap[] tempImage = new Bitmap[1]; // 1

        try {
            tempImage[0] = BitmapFactory.decodeStream(context.getAssets().open(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        texturePool.put(file, loadImage(tempImage));

        for(int i=0; i<tempImage.length; i++){
            tempImage[i].recycle();
        }
    }

    public void addTexture(String file, int cutW, int cutH){
        Bitmap image = null;
        try {
            image = BitmapFactory.decodeStream(context.getAssets().open(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap[] tempImage = new Bitmap[cutH*cutW];
        int w = image.getWidth()/cutW;
        int h = image.getHeight()/cutH;

        for(int i = 0;i<cutH;i++){
            for (int j=0;j<cutW;j++){
                tempImage[j+(cutW*i)] = Bitmap.createBitmap(image,j*w,i*h,w,h);
            }
        }


        texturePool.put(file, loadImage(tempImage));

        for(int i=0; i<tempImage.length; i++){
            tempImage[i].recycle();
        }
    }

    public GLSprite getTexture(String file){
        return new GLSprite(texturePool.get(file));
    }

    private GLSprite loadImage(Bitmap[] tempImage){

        float w = tempImage[0].getWidth();
        float h = tempImage[0].getHeight();

        float xa = -1.0f;
        float xb = 1.0f;
        float ya = 1.0f;
        float yb = -1.0f;

        if(tempImage[0].getWidth() > tempImage[0].getHeight()){
            float calc = 0;

            calc = (float)tempImage[0].getHeight()/(float)tempImage[0].getWidth();
            yb = yb*calc;
            ya = calc;

        } else if(tempImage[0].getWidth() < tempImage[0].getHeight()) {
            float calc = 0.0f;

            calc = (float)tempImage[0].getWidth()/(float)tempImage[0].getHeight();
            xb = calc;
            xa = xa*calc;
        }

        GLVec2f resolution = new GLVec2f(xb, ya);

        xa = xa*3;
        xb = xb*3;
        ya = ya*3;
        yb = yb*3;

        float vertices[] = {
                xa, yb,  //Bottom Left
                xb, yb,  //Bottom Right
                xa, ya,  //Top Left
                xb, ya   //Top Right
        };

        /*
        float xa = 0.0f; //Never change this.
        float xb = 1.0f; //Use to calc correct aspect x from image
        float ya = 0.0f; //Never change this too D:
        float yb = -1.0f; //Use to calc correct aspect y from image

        float vertices[] = {
            0.0f, -1.0f,  //Bottom Left
            1.0f, -1.0f,  //Bottom Right
            0.0f,  0.0f,  //Top Left
            1.0f,  0.0f   //Top Right
        };*/

        float[] textureCoords = {
                0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f
        };

        FloatBuffer mFVertexBuffer;
        FloatBuffer mTextureBuffer;

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mFVertexBuffer = vbb.asFloatBuffer();
        mFVertexBuffer.put(vertices);
        mFVertexBuffer.position(0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);

        int[] textures = new int[tempImage.length];
        int[] textureLoad = new int[tempImage.length];

        for(int i=0; i<tempImage.length; i++){
            gl.glGenTextures(1 , textures, i); // 2
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]); // 3

            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, tempImage[i], 0); // 4

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR); // 5a
            //gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR); // 5b

            textureLoad[i] = textures[i];
        }

        return new GLSprite(textureLoad, mFVertexBuffer, mTextureBuffer, vertices, resolution);
    }

}
