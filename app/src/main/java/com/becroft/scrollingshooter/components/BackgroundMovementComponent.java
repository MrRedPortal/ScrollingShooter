package com.becroft.scrollingshooter.components;

public class BackgroundMovementComponent implements MovementComponent{
    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        return true;
    }
}