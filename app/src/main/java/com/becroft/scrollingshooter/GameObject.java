package com.becroft.scrollingshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.becroft.scrollingshooter.components.GraphicsComponent;
import com.becroft.scrollingshooter.components.InputComponent;
import com.becroft.scrollingshooter.components.MovementComponent;
import com.becroft.scrollingshooter.components.SpawnComponent;
import com.becroft.scrollingshooter.components.Transform;
import com.becroft.scrollingshooter.specs.ObjectSpec;

public class GameObject {

    private Transform transform;
    private boolean isActive = false;
    private String tag;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;


    void setSpawner(SpawnComponent spawner) {
        spawnComponent = spawner;
    }

    void setGraphics(GraphicsComponent graphics, Context context, ObjectSpec objectSpec, PointF objectSize) {
        graphicsComponent = graphics;
        graphics.initialise(context,objectSpec,objectSize);
    }

    void setMovement(MovementComponent movement){
        movementComponent = movement;
    }

    void setInput(InputComponent s){
        s.setTransform(transform);
    }

    void setTag(String newTag){
        tag=newTag;
    }

    void setTransform(Transform t){
        transform = t;
    }

    public void draw(Canvas canvas, Paint paint){
        graphicsComponent.draw(canvas,paint,transform);
    }

    public void update(long fps, Transform playerTransform){
        if(!(movementComponent.move(fps,transform, playerTransform))){
            // Returned false
            isActive =false;
        }
    }

    public boolean spawn(Transform playerTransform){
        // Only spawn component if not already active
        if(!isActive){
            spawnComponent.spawn(playerTransform,transform);
            isActive = true;
            return true;
        }
        return false;
    }

    public boolean checkActive(){
        return isActive;
    }

    String getTag(){
        return tag;
    }

    public void setInactive(){
        isActive = false;
    }

    public Transform getTransform(){
        return transform;
    }

}
