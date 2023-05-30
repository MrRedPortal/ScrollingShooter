package com.becroft.scrollingshooter.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import androidx.constraintlayout.widget.ConstraintSet;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public interface GraphicsComponent {

    void initialise(Context c, ObjectSpec s, PointF objectSize);

    void draw(Canvas canvas, Paint paint, Transform t);

}
