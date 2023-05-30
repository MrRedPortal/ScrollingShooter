package com.becroft.scrollingshooter.engines;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.becroft.scrollingshooter.GameObject;
import com.becroft.scrollingshooter.HUD;
import com.becroft.scrollingshooter.Level;

import java.util.ArrayList;

public class Renderer {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    Renderer(SurfaceView sh){
        surfaceHolder = sh.getHolder();
        paint = new Paint();
    }

    void draw(ArrayList<GameObject> objects, GameState gs, HUD hud, ParticleSystem particleSystem){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,0,0,0));

            if(gs.getDrawing()){
                // Draw all game objects here
                for(GameObject object: objects){
                    if(object.checkActive()){
                        object.draw(canvas,paint);
                    }
                }
            }

            if(gs.getGameOver()){
                // draw background graphic
                objects.get(Level.BACKGROUND_INDEX).draw(canvas,paint);
            }

            // Draw particle system explosion here
            if(particleSystem.isRunning){
                particleSystem.draw(canvas,paint);
            }


            // Draw Hud over everything else
            hud.draw(canvas, paint, gs);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
