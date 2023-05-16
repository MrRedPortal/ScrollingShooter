package com.becroft.scrollingshooter;

import android.graphics.Point;
import android.graphics.PointF;

public class Particle {

    PointF velocity;
    PointF position;

    public Particle(PointF direction){
        velocity = new PointF();
        position = new PointF();

        // Determine Direction
        velocity.x = direction.x;
        velocity.y = direction.y;
    }

    public void update(){
        // Move the particle
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void setPosition(PointF pos){
        position.x = pos.x;
        position.y = pos.y;
    }

    public PointF getPosition(){
        return position;
    }

}
