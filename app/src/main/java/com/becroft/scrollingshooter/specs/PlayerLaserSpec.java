package com.becroft.scrollingshooter.specs;

import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class PlayerLaserSpec extends ObjectSpec {
    private static final String tag = "Player Laser";
    private static final String bitmapName = "player_laser";
    private static final float speed = .65f;
    private static final PointF relativeScale = new PointF(8f,160f);

    private static final String[] components = new String[] {
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"};

    public PlayerLaserSpec(){
        super(tag,bitmapName,speed,relativeScale,components);
    }
}
