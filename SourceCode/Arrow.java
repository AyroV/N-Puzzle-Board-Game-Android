package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Arrow {
    private String type;
    private Arrow_Sprite sprite;
    private int a, b;

    Arrow (String x, Bitmap bmp) {
        type = x;
        sprite = new Arrow_Sprite(bmp);
    }

    //draws the object at given(x, y) position
    void draw(Canvas canvas, double x, double y) {
        sprite.draw(canvas, (int)x, (int)y, type);
        a = (int)x;
        b = (int)y;
    }

    String getType() { return type; }
    Bitmap getSprite() { return sprite.getBit(); }
    int getA() { return a; }
    int getB() { return b; }
}
