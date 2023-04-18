package com.becroft.scrollingshooter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class SoundEngine {
    // for playing sound effects
    private SoundPool sp;
    private int Shoot_ID = -1;
    private int Alien_Explode_ID = -1;
    private int Player_Explode_ID = -1;

    SoundEngine(Context context){
        // init soundpool
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

            sp = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioAttributes).build();
        } else{
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        try{
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;
            // Prepare sounds in memory
            descriptor = assetManager.openFd("shoot.ogg");
            Shoot_ID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("alien_explosion.ogg");
            Alien_Explode_ID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("player_explosion.ogg");
            Player_Explode_ID = sp.load(descriptor, 0);
        } catch (IOException e){
            // Error
        }
    }

    void playShoot(){
        sp.play(Shoot_ID, 1, 1, 0, 0,1);
    }
    void playAlienExplode(){
        sp.play(Alien_Explode_ID, 1, 1, 0, 0,1);
    }
    void playPlayerExplode(){
        sp.play(Player_Explode_ID, 1, 1, 0, 0,1);
    }
}
