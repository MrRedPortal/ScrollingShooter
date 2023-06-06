package com.becroft.scrollingshooter.components;

import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;

import java.util.Random;

public class AlienDiverMovementComponent implements MovementComponent{


    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {

        // Where is the ship?
        PointF location = t.getLocation();
        // How fast is the ship?
        float speed = t.getSpeed();

        // Relative speed difference with player
        float slowDownRelativeToPlayer = 1.8f;

        // Compensate for movement of player when in view
        if(!playerTransform.getFacingRight()){
            location.x += speed * slowDownRelativeToPlayer / fps;
        } else {
            location.x -= speed * slowDownRelativeToPlayer / fps;
        }

        // fall down and respawn at top
        location.y += speed / fps;

        if(location.y > t.getScreenSize().y){
            // Respawn at top
            Random random = new Random();
            location.y = random.nextInt(300) - t.getObjectHeight();

            location.x = random.nextInt((int) t.getScreenSize().x);
        }

        t.updateCollider();
        return true;
    }
}
