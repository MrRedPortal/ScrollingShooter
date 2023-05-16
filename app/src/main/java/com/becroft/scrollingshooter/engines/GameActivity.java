package com.becroft.scrollingshooter.engines;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;

public class GameActivity extends Activity {

    GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        // Bug introduced here
        display.getSize(size);
        // Forgot to set size to the display size.
        // So while things were being drawn, it was to nowhere
        gameEngine = new GameEngine(this, size);

        setContentView(gameEngine);
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameEngine.startThread();
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameEngine.stopThread();
    }
}