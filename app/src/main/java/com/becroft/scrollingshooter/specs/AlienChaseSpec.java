package com.becroft.scrollingshooter.specs;

import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class AlienChaseSpec extends ObjectSpec {
    // This is all the unique specifications for an alien that chases a player

    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship1";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(15f,15f);

    private static final String[] components = new String[] {
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent"};

    public AlienChaseSpec(){
        super(tag,bitmapName,speed,relativeScale,components);
    }
}
