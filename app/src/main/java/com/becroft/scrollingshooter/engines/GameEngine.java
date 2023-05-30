package com.becroft.scrollingshooter.engines;


import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.becroft.scrollingshooter.GameObject;
import com.becroft.scrollingshooter.HUD;
import com.becroft.scrollingshooter.Level;
import com.becroft.scrollingshooter.UIController;
import com.becroft.scrollingshooter.components.PlayerLaserSpawner;
import com.becroft.scrollingshooter.components.Transform;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements Runnable, GameStarter, GameEngineBroadcaster, PlayerLaserSpawner{

    private Thread thread = null;
    private long FPS;
    private ArrayList<InputObserver> inputObservers = new ArrayList();

    UIController uiController;

    private GameState gameState;
    private SoundEngine soundEngine;
    HUD hud;
    Renderer renderer;
    ParticleSystem particleSystem;
    PhysicsEngine physicsEngine;
    Level level;
    public GameEngine(Context context, Point size){
        super(context);

        uiController = new UIController(this);
        gameState = new GameState(this, context);
        soundEngine = new SoundEngine(context);
        hud = new HUD(size);
        renderer = new Renderer(this);
        physicsEngine = new PhysicsEngine();

        particleSystem = new ParticleSystem();
        // Set how many particles
        particleSystem.init(1000);

        level = new Level(context, new PointF(size.x,size.y), this);
    }

    @Override
    public void run() {
        while(gameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> objects = level.getGameObjects();

            if (!gameState.getPaused()) {
                // Update all game objects in a new way

                // This will evolve as the program evolves
                if(physicsEngine.update(FPS,objects, gameState,soundEngine,particleSystem)){

                    //Player hit
                    deSpawnReSpawn();
                }
            }

            // Draw all game objects here in a new way
            renderer.draw(objects,gameState,hud,particleSystem);

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
        for (InputObserver o: inputObservers) {
            o.handleInput(motionEvent, gameState, hud.getControls());
        }

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
        ArrayList<GameObject> objects = level.getGameObjects();

        for(GameObject o: objects){
            o.setInactive();
        }
        objects.get(Level.PLAYER_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getTransform());

        objects.get(Level.BACKGROUND_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getTransform());
    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }

    @Override
    public boolean spawnPlayerLaser(Transform transform) {
        ArrayList<GameObject> objects = level.getGameObjects();

        if(objects.get(Level.nextPlayerLaser).spawn(transform)){
            Level.nextPlayerLaser++;
            soundEngine.playShoot();
            if(Level.nextPlayerLaser == Level.LAST_PLAYER_LASER + 1){
                // Used last laser
                Level.nextPlayerLaser = Level.FIRST_PLAYER_LASER;
            }
        }
        return true;
    }
}
