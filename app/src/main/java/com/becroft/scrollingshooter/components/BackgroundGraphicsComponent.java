package com.becroft.scrollingshooter.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class BackgroundGraphicsComponent implements GraphicsComponent{

    private Bitmap bitmap;
    private Bitmap reverseBitmap;


    @Override
    public void initialise(Context c, ObjectSpec s, PointF screenSize) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t) {

    }
}
