package com.example.alexander.shapespainter;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by Alexander on 19.01.2017.
 */

public class Vector2f {
    private float mX;
    private float mY;
    Vector2f(float x,float y){
        mX = x;
        mY = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
