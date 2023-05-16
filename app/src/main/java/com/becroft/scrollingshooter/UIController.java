package com.becroft.scrollingshooter;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.becroft.scrollingshooter.engines.GameEngineBroadcaster;
import com.becroft.scrollingshooter.engines.GameState;
import com.becroft.scrollingshooter.engines.InputObserver;

import java.util.ArrayList;

public class UIController implements InputObserver {

    public UIController(GameEngineBroadcaster b){
        b.addObserver(this);
    }


    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        int eventType = event.getAction() & MotionEvent.ACTION_MASK;

        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if (controls.get(HUD.PAUSE).contains(x, y)) {
                // Player pressed pause
                // Respond differently depending on gameState

                // if game not paused
                if(!gs.getPaused()){
                    gs.pause();
                }
                // If game is over start new game
                else if(gs.getGameOver()){
                    gs.startNewGame();
                }
                // Paused and not gameOver
                else if(gs.getPaused() && !gs.getGameOver()){
                    gs.resume();
                }
            }
        }
    }
}
