package com.example.alexander.shapespainter;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by Alexander on 19.01.2017.
 */

public class VectorSize2f {
    private float mWidth;
    private float mHeight;

    VectorSize2f(float w, float h){
        mWidth = w;
        mHeight = h;
    }

    public float getmWidth() {
        return x;
    }

    public float getmHeight() {
        return y;
    }
}
