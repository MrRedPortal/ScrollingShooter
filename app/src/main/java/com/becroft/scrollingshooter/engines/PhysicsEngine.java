package com.becroft.scrollingshooter.engines;

import com.becroft.scrollingshooter.GameObject;
import com.becroft.scrollingshooter.Level;

import java.util.ArrayList;

public class PhysicsEngine {

    // This will change as we develop the project

    boolean update(long FPS, ArrayList<GameObject> objects, GameState gs, SoundEngine se,ParticleSystem particleSystem){

        // Update all game objects
        for(GameObject object: objects){
            if(object.checkActive()){
                object.update(FPS,objects.get(Level.PLAYER_INDEX).getTransform());
            }
        }


        if(particleSystem.isRunning){
            particleSystem.update(FPS);
        }

        return false;
    }

    // Collision detection here

}
