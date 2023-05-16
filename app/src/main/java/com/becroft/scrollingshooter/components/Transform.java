package com.becroft.scrollingshooter.components;

import android.graphics.PointF;
import android.graphics.RectF;

import kotlin.collections.UCollectionsKt;

public class Transform {

    // These two member variables are for scrolling background
    private int xClip;
    private boolean reversedFirst = false;

    private RectF collider;
    private PointF location;
    private boolean facingRight = true;
    private boolean headingUp = false;
    private boolean headingDown = false;
    private boolean headingLeft = false;
    private boolean headingRight = false;
    private float speed;
    private float objectHeight;
    private float objectWidth;
    private static PointF screenSize;

    Transform(float speed, float objectWidth, float objectHeight, PointF startingLocation, PointF screenSize){

        collider = new RectF();
        this.speed = speed;
        this.objectHeight = objectHeight;
        this.objectWidth = objectWidth;
        location = startingLocation;
        screenSize = screenSize;

    }

    // Helper methods that the background will use
    boolean getReverseFirst(){
        return reversedFirst;
    }

    void flipReversedFirst(){
        reversedFirst = !reversedFirst;
    }

    int getXClip(){
        return xClip;
    }

    void setXClip(int newXClip){
        xClip = newXClip;
    }

    PointF getScreenSize(){
        return screenSize;
    }

    void headUp(){
        headingUp = true;
        headingDown = false;
    }

    void headDown(){
        headingUp = false;
        headingDown = true;
    }

    void headRight(){
        headingRight = true;
        headingLeft = false;
    }

    void headLeft(){
        headingRight = false;
        headingLeft = true;
    }

    boolean headingDown(){
        return headingDown;
    }

    boolean headingUp(){
        return headingUp;
    }

    boolean headingRight(){
        return headingRight;
    }

    boolean headingLeft(){
        return headingLeft;
    }

    void updateCollider(){
        // Pull borders in a bit(10%)
        collider.top = location.y + (objectHeight / 10);
        collider.left = location.x + (objectWidth / 10);
        collider.bottom = (location.y + objectHeight) - (objectHeight / 10);
        collider.right = (location.x + objectWidth) - (objectWidth / 10);
    }

    float getObjectHeight(){
        return objectHeight;
    }

    void stopVertical(){
        headingDown = false;
        headingUp = false;
    }

    float getSpeed(){
        return speed;
    }

    void setLocation(float horizontal, float vertical){
        location = new PointF(horizontal,vertical);
        updateCollider();
    }

    PointF getLocation(){
        return location;
    }

    PointF getSize(){
        return new PointF(objectWidth, objectHeight);
    }

    void flip(){
        facingRight = !facingRight;
    }

    boolean getFacingRight(){
        return facingRight;
    }

    RectF getCollider(){
        return collider;
    }

    PointF getFiringLocation(float laserLength){
        PointF firingLocation = new PointF();
        if(facingRight) {
            firingLocation.x = location.x + (objectWidth / 8f);
        } else {
            firingLocation.x = location.x + (objectWidth / 8f) - laserLength;
        }

        // Move the height down a bit form ship origin
        firingLocation.y = location.y + (objectHeight / 1.28f);
        return firingLocation;
    }

}
