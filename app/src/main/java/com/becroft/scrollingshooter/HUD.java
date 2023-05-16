package com.becroft.scrollingshooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.becroft.scrollingshooter.engines.GameState;

import java.util.ArrayList;

public class HUD {
    private int textFormatting;
    private int screenHeight;
    private int screenWidth;

    private ArrayList<Rect> controls;

    public static int UP = 0;
    public static int DOWN = 1;
    public static int FLIP = 2;
    public static int SHOOT = 3;
    public static int PAUSE = 4;

    public HUD(Point size){
        screenHeight = size.y;
        screenWidth = size.x;
        textFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls(){
        int buttonWidth = screenWidth / 14;
        int buttonHeight = screenHeight / 12;
        int buttonPadding = screenWidth / 90;

        Rect up = new Rect(buttonPadding, screenHeight - (buttonHeight*2) - (buttonPadding*2),
                buttonWidth + buttonPadding, screenHeight - buttonHeight - (buttonPadding*2));

        Rect down = new Rect(buttonPadding, screenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding, screenHeight-buttonPadding);

        Rect flip = new Rect(screenWidth - buttonPadding - buttonWidth, screenHeight - buttonHeight - buttonPadding,
                screenWidth - buttonPadding, screenHeight - buttonPadding);

        Rect shoot = new Rect(screenWidth - buttonPadding - buttonWidth,
                screenHeight - (buttonHeight*2) - (buttonPadding*2), screenWidth -buttonPadding,
                screenHeight - buttonHeight - (buttonPadding*2));

        Rect pause = new Rect(screenWidth - buttonPadding - buttonWidth, buttonPadding,screenWidth - buttonPadding, buttonPadding + buttonHeight);

        controls = new ArrayList<>();
        controls.add(UP,up);
        controls.add(DOWN,down);
        controls.add(FLIP,flip);
        controls.add(SHOOT,shoot);
        controls.add(PAUSE,pause);
    }
    public void draw(Canvas c, Paint p, GameState gs){
        // Draw the hud
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(textFormatting);
        c.drawText("Hi: " + gs.getHighScore(), textFormatting, textFormatting, p);
        c.drawText("Score: " + gs.getScore(), textFormatting, textFormatting * 2, p);
        c.drawText("Lives: " + gs.getNumShips(), textFormatting, textFormatting * 3, p);

        if(gs.getGameOver()){
            p.setTextSize(textFormatting*5);
            c.drawText("PRESS PLAY", screenWidth/4, screenHeight/2,p);
        }

        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(textFormatting*5);
            c.drawText("PAUSED", screenWidth/3,screenHeight/2,p);
        }

        drawControls(c,p);
    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));
        for (Rect r: controls) {
            c.drawRect(r.left,r.top,r.right,r.bottom, p);
        }

        p.setColor(Color.argb(255,255,255,255));
    }

    public ArrayList<Rect> getControls(){
        return controls;
    }
}
