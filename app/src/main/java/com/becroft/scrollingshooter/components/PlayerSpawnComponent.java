package com.becroft.scrollingshooter.components;

public class PlayerSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // Spawn in center of screen
        t.setLocation(t.getScreenSize().x/2, t.getScreenSize().y/2);
    }
}
