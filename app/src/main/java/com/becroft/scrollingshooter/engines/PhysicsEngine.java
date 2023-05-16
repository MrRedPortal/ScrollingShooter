package com.becroft.scrollingshooter.engines;

public class PhysicsEngine {

    // This will change as we develop the project

    boolean update(long FPS, ParticleSystem particleSystem){
        if(particleSystem.isRunning){
            particleSystem.update(FPS);
        }

        return false;
    }

    // Collision detection here

}
