package com.example.alexander.shapespainter;

import android.graphics.Color;

import javax.vecmath.Vector2f;

public class ConstWorld {
    static final  int YELLOW = Color.YELLOW;
    static final  int BLUE = Color.BLUE;
    static final  int WHITE = Color.WHITE;
    static final  int BLACK = Color.BLACK;
    static final  int SHAPE_DIAGRAM_COLOR = Color.CYAN;
    static final  int DRAG_POINT_COLOR = Color.BLUE;

    static final Vector2f INITIALIZATION_SHAPE_SIZE = new Vector2f(50, 50);
    static final Vector2f DRAG_POINT_SIZE =  new Vector2f(5, 5);
    static final int DEFAULT_BUTTON_SIZE = 80;
    static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_X = 300;
    static final int DEFAULT_SHIFT_FOR_MOVING_TOOLBAR = 100;
    static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_Y = 9;
}
