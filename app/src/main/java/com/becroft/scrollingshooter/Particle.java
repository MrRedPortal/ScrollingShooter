package com.becroft.scrollingshooter;

import android.graphics.Point;
import android.graphics.PointF;

public class Particle {

    PointF velocity;
    PointF position;

    Particle(PointF direction){
        velocity = new PointF();
        position = new PointF();

        // Determine Direction
        velocity.x = direction.x;
        velocity.y = direction.y;
    }

    void update(){
        // Move the particle
        position.x += velocity.x;
        position.y += velocity.y;
    }

    void setPosition(PointF pos){
        position.x = pos.x;
        position.y = pos.y;
    }

    PointF getPosition(){
        return position;
    }

}
