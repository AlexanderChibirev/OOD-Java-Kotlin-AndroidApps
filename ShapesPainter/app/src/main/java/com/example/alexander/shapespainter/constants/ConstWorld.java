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
    public static final int DEFAULT_SHIFT_POSITION_X_FOR_UNDO_TOOLBAR = 300;
    public static final int DEFAULT_SHIFT_POSITION_X_FOR_REDO_TOOLBAR = 200;
    public static final int DEFAULT_SHIFT_POSITION_X_FOR_TRASH_TOOLBAR = 100;

    public static final int DEFAULT_SHIFT_FOR_TOOLBAR = 100;
    public static final int DEFAULT_SHIFT_FOR_START_TOOLBAR_Y = 9;

    public static final Vector2f DEFAULT_CENTER_POSITION_TRIANGLE_TOOLBAR = new Vector2f(60, 45);
    public static final Vector2f DEFAULT_CENTER_POSITION_RECTANGLE_TOOLBAR = new Vector2f(250, 45);
    public static final Vector2f DEFAULT_CENTER_POSITION_ELLIPSE_TOOLBAR = new Vector2f(150, 45);

    public static final Vector2f DEFAULT_SIZE_TRIANGLE_TOOLBAR = new Vector2f(80, 50);
    public static final Vector2f DEFAULT_SIZE_RECTANGLE_TOOLBAR = new Vector2f(80, 50);
    public static final Vector2f DEFAULT_SIZE_ELLIPSE_TOOLBAR = new Vector2f(50, 50);
}
