package com.becroft.scrollingshooter.specs;

import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class BackgroundSpec extends ObjectSpec {
    // This is all the unique specifications required for the background

    private static final String tag = "Background";
    private static final String bitmapName = "background";
    private static final float speed = 2f;
    private static final PointF relativeScale = new PointF(1f,1f);

    private static final String[] components = new String[] {
            "BackgroundGraphicsComponent",
            "BackgroundMovementComponent",
            "BackgroundSpawnComponent"};

    public BackgroundSpec(){
        super(tag,bitmapName,speed,relativeScale,components);
    }
}
