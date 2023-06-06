package com.becroft.scrollingshooter.components;

// This allows the alien to communicate with the game engine to spawn a laser
public interface AlienLaserSpawner {
    void spawnAlienLaser(Transform transform);
}
