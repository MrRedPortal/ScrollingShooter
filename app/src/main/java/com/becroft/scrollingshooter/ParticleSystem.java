package com.becroft.scrollingshooter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {

    float duration;

    ArrayList<Particle> particles;
    Random random = new Random();
    boolean isRunning = false;

    void init(int numParticles){
        particles = new ArrayList<>();
        // Create particles
        for(int i = 0; i<numParticles; i++){
            float angle = (random.nextInt(360));
            angle = angle * 3.14f / 180.f;
            float speed = (random.nextInt(20)+1);

            PointF direction;

            direction = new PointF((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);

            particles.add(new Particle(direction));
        }
    }

    void update(Long FPS){

        duration -= (1f/FPS);

        for (Particle p: particles) {
            p.update();
        }

        if(duration < 0){
            isRunning = false;
        }

    }

    void emitParticles(PointF startPosition){
        isRunning = true;
        duration = 1f;

        for (Particle p: particles) {
            p.setPosition(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint){

        for (Particle p: particles) {

            // Coloured Particles
            paint.setARGB(255,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));

            // White Particles
            // paint.setARGB(255,255,255,255);

            canvas.drawRect(p.getPosition().x, p.getPosition().y, p.getPosition().x+25, p.getPosition().y+25, paint);
        }
    }

}
