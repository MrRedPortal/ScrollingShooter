package com.becroft.scrollingshooter;

import android.content.Context;
import android.graphics.PointF;

import com.becroft.scrollingshooter.components.*;
import com.becroft.scrollingshooter.engines.GameEngine;
import com.becroft.scrollingshooter.specs.ObjectSpec;

import java.util.concurrent.CopyOnWriteArraySet;

public class GameObjectFactory {

    private Context context;
    private PointF screenSize;
    private GameEngine gameEngineReference;

    GameObjectFactory(Context c, PointF ss, GameEngine engine){
        this.context = c;
        this.screenSize = ss;
        this.gameEngineReference = engine;
    }

    GameObject create(ObjectSpec spec){
        GameObject object = new GameObject();

        int numComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        object.setTag(spec.getTag());
        // Configure speed relative to the screen size
        float speed = screenSize.x / spec.getSpeed();

        // Configure the object size relative to the screen
        PointF objectSize = new PointF(screenSize.x/spec.getSizeScale().x,screenSize.y/spec.getSizeScale().y);

        // set the location to somewhere off screen
        PointF location =  new PointF(HIDDEN,HIDDEN);

        object.setTransform(new Transform(speed, objectSize.x, objectSize.y, location, screenSize));

        // Loop through and initialise al the components
        for(int i = 0; i<numComponents; i++){
            switch(spec.getComponents()[i]){
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent(gameEngineReference));
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new stdGraphicsComponent(),context,spec,objectSize);
                    break;
                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;
                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;
                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawnComponent());
                    break;
                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(), context, spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                case "AlienChaseMovementComponent":
                    object.setMovement(new AlienChaseMovementComponent(gameEngineReference));
                    break;
                case "AlienPatrolMovementComponent":
                    object.setMovement(new AlienPatrolMovementComponent(gameEngineReference));
                    break;
                case "AlienDiverMovementComponent":
                    object.setMovement(new AlienDiverMovementComponent());
                    break;
                case "AlienHorizontalSpawnComponent":
                    object.setSpawner(new AlienHorizontalSpawnComponent());
                    break;
                case "AlienVerticalSpawnComponent":
                    object.setSpawner(new AlienVerticalSpawnComponent());
                    break;

                default:
                    // Error unidentified component
                    break;
            }
        }
        // Return the completed game object to the level class
        return object;
    }
}
