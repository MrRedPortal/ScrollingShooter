package com.becroft.scrollingshooter.components;

import android.graphics.Rect;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

import com.becroft.scrollingshooter.HUD;
import com.becroft.scrollingshooter.engines.GameEngine;
import com.becroft.scrollingshooter.engines.GameState;
import com.becroft.scrollingshooter.engines.InputObserver;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver {

    private Transform transform;
    private PlayerLaserSpawner PLS;

    public PlayerInputComponent(GameEngine gameEngine){
        gameEngine.addObserver(this);
        PLS = gameEngine;
    }



    @Override
    public void setTransform(Transform t) {
        transform = t;
    }


    // Require method of input observer interfacae called from the onTouchEvent method
    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (controls.get(HUD.UP).contains(x,y) || controls.get(HUD.DOWN).contains(x,y)){
                    // player has released up or down
                    transform.stopVertical();
                }
                break;

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (controls.get(HUD.UP).contains(x,y)){
                    // Player pressed UP
                    transform.headUp();
                } else if (controls.get(HUD.DOWN).contains(x,y)) {
                    // Player has pressed DOWN
                    transform.headDown();
                } else if (controls.get(HUD.FLIP).contains(x,y)){
                    // player has released flip button
                    transform.flip();
                } else if(controls.get(HUD.SHOOT).contains(x,y)){
                    // Player has pressed SHOOT
                    PLS.spawnPlayerLaser(transform);
                }
                break;
        }
    }
}
