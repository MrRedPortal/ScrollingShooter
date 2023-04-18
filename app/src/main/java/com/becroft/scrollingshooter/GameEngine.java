package com.becroft.scrollingshooter;


import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView implements Runnable, GameStarter {

    private Thread thread = null;
    private long FPS;

    private GameState gameState;
    private SoundEngine soundEngine;
    HUD hud;
    Renderer renderer;
    public GameEngine(Context context, Point size){
        super(context);

        gameState = new GameState(this, context);
        soundEngine = new SoundEngine(context);
        hud = new HUD(size);
        renderer = new Renderer(this);
    }

    @Override
    public void run() {
        while(gameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();

            if (gameState.getPaused()) {
                // Update all game objects in a new way
            }

            // Draw all game objects here in a new way
            renderer.draw(gameState,hud);

            // Measure FPS
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;
                FPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        // Handle player input in new way



        return true;
    }

    public void stopThread(){
        // New code soon
        gameState.stopEverything();

        try {
            thread.join();
        } catch (InterruptedException e){
            Log.e("Exception", "StopThread() " + e.getMessage());
        }
    }

    public void startThread(){
        // new code here soon
        gameState.startThread();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void deSpawnReSpawn() {
        // Eventually this will despawn and then respawn all game objects
    }
}
