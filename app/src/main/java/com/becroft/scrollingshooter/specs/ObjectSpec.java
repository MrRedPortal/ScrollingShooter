package com.becroft.scrollingshooter.specs;

import android.graphics.PointF;

abstract class ObjectSpec {

    private String tag;
    private String bitmapName;
    private float speed;
    private PointF sizeScale;
    private String[] components;

    ObjectSpec(String tag, String bitmapName, float speed, PointF sizeScale, String[] components) {
        this.tag = tag;
        this.bitmapName = bitmapName;
        this.speed = speed;
        this.sizeScale = sizeScale;
        this.components = components;
    }

    public String getTag() {
        return tag;
    }

    public String getBitmapName() {
        return bitmapName;
    }

    public float getSpeed() {
        return speed;
    }

    public PointF getSizeScale() {
        return sizeScale;
    }

    public String[] getComponents() {
        return components;
    }
}
