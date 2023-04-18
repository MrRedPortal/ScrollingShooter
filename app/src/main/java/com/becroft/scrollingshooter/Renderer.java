package com.becroft.scrollingshooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Renderer {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    Renderer(SurfaceView sh){
        surfaceHolder = sh.getHolder();
        paint = new Paint();
    }

    void draw(GameState gs, HUD hud){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,0,0,0));

            if(gs.getDrawing()){
                // Draw all game objects here
            }

            if(gs.getGameOver()){
                // draw background graphic
            }

            // Draw particle system explosion here

            // Draw Hud over everything else
            hud.draw(canvas, paint, gs);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
