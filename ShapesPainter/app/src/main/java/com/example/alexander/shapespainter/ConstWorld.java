package com.example.alexander.shapespainter;

import android.graphics.Color;

import javax.vecmath.Vector2f;

class ConstWorld {
    static final int YELLOW = Color.YELLOW;
    static final int BLUE = Color.BLUE;
    static final int WHITE = Color.WHITE;
    static final int BLACK = Color.BLACK;
    static final int SHAPE_DIAGRAM_COLOR = Color.CYAN;
    static final int DRAG_POINT_COLOR = Color.BLUE;


    private static final Vector2f SHAPE_MAX_SIZE = new Vector2f(200f, 200f);
    private static final Vector2f SHAPE_MIN_SIZE = new Vector2f(25f, 25f);
    private static final float SHAPE_DEFAULT_WIDTH =  100f;
    private static final float SHAPE_DEFAULT_HEIGHT = 25f;
    private static final float SHAPE_DEFAULT_POSITION = 400f;
    static final Vector2f DRAG_POINT_SIZE = new Vector2f(5, 5);
    static final int DEFAULT_BUTTON_RESIZE = 80;
    static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_X = 300;
    static final int DEFAULT_SHIFT_FOR_MOVING_TOOLBAR = 100;
    static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_Y = 9;
}
