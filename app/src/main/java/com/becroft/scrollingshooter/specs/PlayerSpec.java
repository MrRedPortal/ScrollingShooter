package com.becroft.scrollingshooter.specs;

import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class PlayerSpec extends ObjectSpec {
    private static final String tag = "Player";
    private static final String bitmapName = "player_ship";
    private static final float speed = 1f;
    private static final PointF relativeScale = new PointF(15f,15f);

    private static final String[] components = new String[] {
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"};

    public PlayerSpec(){
        super(tag,bitmapName,speed,relativeScale,components);
    }
}
