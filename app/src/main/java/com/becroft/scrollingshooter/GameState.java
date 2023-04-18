package com.becroft.scrollingshooter;

import android.content.Context;
import android.content.SharedPreferences;

public class GameState {

    private static volatile boolean threadRunning = false;
    private static volatile boolean paused = true;
    private static volatile boolean gameOver = true;
    private static volatile boolean drawing = false;

    // This object will have access to the spawn method in GameEngine once it is initialised
    private GameStarter gameStarter;

    private int score;
    private int highScore;
    private int numShips;

    // This is how we make highscores persist
    private SharedPreferences.Editor editor;

    GameState(GameStarter gs, Context context){
        // This inits the gameStarter reference
        gameStarter = gs;

        // get the current highscore
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        // init editor
        editor = prefs.edit();

        // Load high score from entry in the file labelled HiScore
        // if not available set = 0
        highScore = prefs.getInt("hi_score", 0);
    }

    private void endGame(){
        gameOver = true;
        paused = true;
        if(score>highScore){
            highScore = score;
            // Save high score
            editor.putInt("hi_score", highScore);
            editor.commit();
        }
    }

    void startNewGame(){
        score = 0;
        numShips = 3;
        // We dont want to draw objects while deSpawnReSpawn is clearing and respawning
        stopDrawing();
        gameStarter.deSpawnReSpawn();
        resume();

        // start drawing
        startDrawing();

    }

    void loseLife(SoundEngine se){
        numShips--;
        se.playPlayerExplode();
        if(numShips == 0){
            pause();
            endGame();
        }
    }

    //Getters and setters
    int getNumShips(){
        return numShips;
    }

    void increaseScore(){
        score++;
    }

    int getScore(){
        return score;
    }

    int getHighScore(){
        return highScore;
    }

    void pause(){
        paused = true;
    }

    void resume(){
        gameOver = false;
        paused = false;
    }

    void stopEverything(){
        paused = true;
        gameOver = true;
        threadRunning = false;
    }

    boolean getThreadRunning(){
        return threadRunning;
    }

    void startThread(){
        threadRunning = true;
    }

    private void stopDrawing(){
        drawing = false;
    }

    private void startDrawing(){
        drawing = true;
    }

    boolean getDrawing(){
        return drawing;
    }

    boolean getPaused(){
        return paused;
    }

    boolean getGameOver(){
        return gameOver;
    }
}
