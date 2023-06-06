package com.becroft.scrollingshooter.components;

import java.util.Random;

public class AlienVerticalSpawnComponent implements SpawnComponent{

    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // Spawn just off screen randomly but within screen width
        Random random = new Random();

        float xPosition = random.nextInt((int) t.getScreenSize().x);

        // Set height vertically
        float spawnHeight = random.nextInt(300) - t.getObjectHeight();

        t.setLocation(xPosition,spawnHeight);
        t.headDown();
    }
}
