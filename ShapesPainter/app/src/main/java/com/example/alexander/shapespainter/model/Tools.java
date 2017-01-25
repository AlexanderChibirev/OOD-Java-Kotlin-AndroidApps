package com.example.alexander.shapespainter.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alexander.shapespainter.R;
import com.example.alexander.shapespainter.utils.PainterUtils;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.ConstWorld.*;

public class Tools {

    private ShapesList mToolsList = new ShapesList();
    private Map<Bitmap, Vector2f> mBitmapsList = new HashMap<>();

    public Tools(Context context, int screenWidth) {
        initTools(context, screenWidth);
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(
                DEFAULT_CENTER_POSITION_TRIANGLE_TOOLBAR,
                DEFAULT_SIZE_TRIANGLE_TOOLBAR.x,
                DEFAULT_SIZE_TRIANGLE_TOOLBAR.y,
                ShapeType.Triangle);
        mToolsList.addShape(shape);
        shape = shapeFactory.createShape(
                DEFAULT_CENTER_POSITION_RECTANGLE_TOOLBAR,
                DEFAULT_SIZE_RECTANGLE_TOOLBAR.x,
                DEFAULT_SIZE_RECTANGLE_TOOLBAR.y,
                ShapeType.Rectangle);
        mToolsList.addShape(shape);
        shape = shapeFactory.createShape(
                DEFAULT_CENTER_POSITION_ELLIPSE_TOOLBAR,
                DEFAULT_SIZE_ELLIPSE_TOOLBAR.x,
                DEFAULT_SIZE_ELLIPSE_TOOLBAR.y,
                ShapeType.Ellipse);
        mToolsList.addShape(shape);

    }

    private void initTools(Context context, int screenWidth) {
        int resizeValue = DEFAULT_BUTTON_RESIZE;
        Bitmap mBitmapTools;
        Vector2f undoPosition = new Vector2f(
                screenWidth - DEFAULT_SHIFT_POSITION_X_FOR_UNDO_TOOLBAR,
                DEFAULT_SHIFT_FOR_START_TOOLBAR_Y);
        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmapsList.put(mBitmapTools, undoPosition);

        Vector2f positionRedo = new Vector2f(
                screenWidth - DEFAULT_SHIFT_POSITION_X_FOR_REDO_TOOLBAR,
                DEFAULT_SHIFT_FOR_START_TOOLBAR_Y);
        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmapsList.put(mBitmapTools, positionRedo);

        Vector2f trashPosition = new Vector2f(screenWidth - DEFAULT_SHIFT_POSITION_X_FOR_TRASH_TOOLBAR, DEFAULT_SHIFT_FOR_START_TOOLBAR_Y);
        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmapsList.put(mBitmapTools, trashPosition);
    }

    public Map<Bitmap, Vector2f> getBitmapToolsList() {
        return mBitmapsList;
    }

    public ShapesList getToolsList() {
        return mToolsList;
    }
}
