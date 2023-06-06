package com.becroft.scrollingshooter.components;

import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.AlienLaserSpec;

import java.util.Random;

public class AlienChaseMovementComponent implements MovementComponent {

    private Random randomShot = new Random();

    // Gives the class the ability to tell the game engine to spawn a laser
    private AlienLaserSpawner alienLaserSpawner;

    public AlienChaseMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }

    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {

        // 1 in 100 chance of shot being fired when in line with player
        final int TAKE_SHOT=0;
        final int SHOT_CHANCE = 100;

        // How wide is the screen?
        float screenWidth = t.getScreenSize().x;
        // Where is the player
        PointF playerLocation = playerTransform.getLocation();

        // How tall is the ship
        float height = t.getObjectHeight();
        // Is the ship facing right?
        boolean facingRight = t.getFacingRight();
        // How far before ship doesnt bother chasing
        float chasingDistance = t.getScreenSize().x/3f;
        // How far can the AI see?
        float seeingDistance = t.getScreenSize().x/1.5f;
        // Where is the ship
        PointF location = t.getLocation();
        // How fast is the ship moving?
        float speed = t.getSpeed();

        // Relative speed difference with player
        float verticalSpeedDifference = .3f;
        float slowDownRelativeToPLayer = 1.8f;

        // Prevent ship locking on too accurately
        float verticalSearchBounce = 20f;

        // Move in the direction of the player but relative to the players direction of travel
        if(Math.abs(location.x-playerLocation.x)>chasingDistance){
            if(location.x<playerLocation.x){
                t.headRight();
            } else if (location.x>playerLocation.x) {
                t.headLeft();
            }
        }

        // Can the alien "see" the player? If so try and align vertically
        if(Math.abs(location.x-playerLocation.x)<=seeingDistance){
            if((int) location.y-playerLocation.y < -verticalSearchBounce){
                t.headDown();
            } else if((int) location.y - playerLocation.y > verticalSearchBounce){
                t.headUp();
            }
            // Compensate for relative movement of player to alien, but only when in view
            if(!playerTransform.getFacingRight()){
                location.x += speed * slowDownRelativeToPLayer / fps;
            } else {
                location.x -= speed *slowDownRelativeToPLayer / fps;
            }
        } else {
            // Stop vertical movement to prevent alien disappearing off top or bottom
            t.stopVertical();
        }

        // Moving vertically is slower than horizontally
        // Adjust to make game harder
        if(t.headingDown()){
            location.y += speed *verticalSpeedDifference/fps;
        } else if (t.headingUp()) {
            location.y -= speed * verticalSpeedDifference/fps;
        }

        // Move horizontally
        if(t.headingLeft()){
            location.x -= speed/fps;
        }
        if(t.headingRight()){
            location.x += speed/fps;
        }

        // Update the collider
        t.updateCollider();


        // Shoot if the alien is within a ships height above, below, or in line with the player
        // Could hit or miss
        if(randomShot.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if(Math.abs(playerLocation.y - location.y)< height){
                // Is the alien facing the right direction and close enough to the player
                if((facingRight && playerLocation.x > location.x || !facingRight && playerLocation.x < location.x)
                        && Math.abs(playerLocation.x - location.x)< screenWidth){

                    // Fire!
                    alienLaserSpawner.spawnAlienLaser(t);
                }
            }
        }
        return true;
    }
}
