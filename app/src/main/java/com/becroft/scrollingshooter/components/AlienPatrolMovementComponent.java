package com.becroft.scrollingshooter.components;

import android.graphics.PointF;

import java.util.Random;

public class AlienPatrolMovementComponent implements MovementComponent{

    private AlienLaserSpawner alienLaserSpawner;
    private Random shotRandom = new Random();

    public AlienPatrolMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }


    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        // 1 in 100 chance of shot being fired when in line with player
        final int TAKE_SHOT=0;
        final int SHOT_CHANCE = 100;

        // Where is the player
        PointF playerLocation = playerTransform.getLocation();

        // The top of the screen
        final float MIN_VERTICAL_BOUNDS = 0;
        // The Width and height of screen
        float screenX = t.getScreenSize().x;
        float screenY = t.getScreenSize().y;

        // How far can alien see
        float seeingDistance = screenX * .5f;

        // Where is the alien
        PointF location = t.getLocation();
        // Speed
        float speed = t.getSpeed();
        // Height
        float height = t.getObjectHeight();
        // Stop alien going too far away
        float MAX_VERTICAL_BOUNDS = screenY - height;
        final float MAX_HORIZONTAL_BOUNDS = 2 * screenX;
        final float MIN_HORIZONTAL_BOUNDS = 2 * -screenX;

        // Adjust speed relative
        float horizontalSpeedAdjustmentRelativeToPLayer = 0;
        // How much to speed up or slow down
        float horizontalSpeedAdjustmentModifier = .8f;

        // Can alien see player? if so make speed relative
        if(Math.abs(location.x - playerLocation.x) < seeingDistance){
            if(playerTransform.getFacingRight() != t.getFacingRight()){
                // facing different way, speed up alien
                horizontalSpeedAdjustmentRelativeToPLayer = speed * horizontalSpeedAdjustmentModifier;
            } else {
                horizontalSpeedAdjustmentRelativeToPLayer = -(speed * horizontalSpeedAdjustmentModifier);
            }
        }

        // move based on speed modification
        if(t.headingLeft()){
            location.x -= (speed + horizontalSpeedAdjustmentRelativeToPLayer) /fps;

            // turn the ship around if it reaches the barrier of its patrol area
            if(location.x>MAX_HORIZONTAL_BOUNDS){
                location.x=MAX_HORIZONTAL_BOUNDS;
                t.headLeft();
            }
        }

        // Vertical speed
        if(t.headingDown()){
            location.y += (speed) /fps;
            if(location.y > MAX_VERTICAL_BOUNDS){
                t.headUp();
            }
        } else {
            location.y -= speed / fps;
            if(location.y<MIN_VERTICAL_BOUNDS){
                t.headDown();
            }
        }

        t.updateCollider();

        // Shoot if the alien aligns with player
        if(shotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if(Math.abs(playerLocation.y-location.y)<height){
                // is alien facing right direction and close enough to the player
                if((t.getFacingRight() && playerLocation.x > location.x || !t.getFacingRight() && playerLocation.x < location.x)
                        && Math.abs(playerLocation.x - location.x) < screenX){
                     // Fire
                    alienLaserSpawner.spawnAlienLaser(t);
                }
            }
        }
        return true;
    }
}
