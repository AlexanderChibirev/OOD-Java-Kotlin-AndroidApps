package com.example.alexander.shapespainter.constants;

import android.graphics.Color;

import javax.vecmath.Vector2f;

public class ConstWorld {
    public static final int YELLOW = Color.YELLOW;
    public static final int BLUE = Color.BLUE;
    public static final int WHITE = Color.WHITE;
    public static final int BLACK = Color.BLACK;
    public static final int SHAPE_DIAGRAM_COLOR = Color.CYAN;
    public static final int DRAG_POINT_COLOR = Color.BLUE;


    public static final Vector2f SHAPE_MAX_SIZE = new Vector2f(200f, 200f);
    public static final Vector2f SHAPE_MIN_SIZE = new Vector2f(25f, 25f);
    public static final float SHAPE_DEFAULT_WIDTH = 100f;
    public static final float SHAPE_DEFAULT_HEIGHT = 25f;
    public static final float SHAPE_DEFAULT_POSITION = 400f;
    public static final Vector2f DRAG_POINT_SIZE = new Vector2f(5, 5);
    public static final int DEFAULT_BUTTON_RESIZE = 80;
    public static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_X = 300;
    public static final int DEFAULT_SHIFT_FOR_TOOLBAR = 100;
    public static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_Y = 9;
}
