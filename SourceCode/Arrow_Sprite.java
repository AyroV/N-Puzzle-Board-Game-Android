package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Arrow_Sprite {
    private Bitmap image;
    private String type;

    Arrow_Sprite(Bitmap bmp) {
        super();
        image = bmp;
    }

    void draw(Canvas canvas, int x, int y, String t) {
        canvas.drawBitmap(image, x, y, null);
        type = t;
    }

    Bitmap getBit() { return image; }
}
