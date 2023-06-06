package com.becroft.scrollingshooter.components;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Random;

public class AlienHorizontalSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // Get screen size
        PointF screenSize = t.getScreenSize();
        // Spawn just off screen left or right
        Random random = new Random();
        boolean left = random.nextBoolean();
        // How far away?
        float distance = random.nextInt(2000) + t.getScreenSize().y;
        // Generate spawn height where entire ship is vertically on screen
        float spawnHeight = random.nextFloat() * screenSize.y - t.getSize().y;
        // Spawn the ship
        if(left){
            t.setLocation(-distance, spawnHeight);
            t.headRight();
        } else {
            t.setLocation(distance, spawnHeight);
            t.headLeft();
        }
    }
}
