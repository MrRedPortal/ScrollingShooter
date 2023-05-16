package com.becroft.scrollingshooter.components;

public interface MovementComponent {

    boolean move(long fps, Transform t, Transform playerTransform);

}

