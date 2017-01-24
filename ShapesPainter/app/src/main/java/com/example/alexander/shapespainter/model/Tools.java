package com.example.alexander.shapespainter.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alexander.shapespainter.R;
import com.example.alexander.shapespainter.ShapesList;
import com.example.alexander.shapespainter.utils.PainterUtils;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_BUTTON_RESIZE;
import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;
import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_SHIFT_FOR_TOOLBAR;

public class Tools {

    private ShapesList mToolsList = new ShapesList();
    private Map<Bitmap, Vector2f> mBitmaps = new HashMap<>();

    public Tools(Context context, int screenWidth) {
        initTools(context, screenWidth);
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(new Vector2f(150, 45), 26, 26, ShapeType.Ellipse);
        mToolsList.addShape(shape);
        shape = shapeFactory.createShape(new Vector2f(220, 20), 80, 50, ShapeType.Rectangle);
        mToolsList.addShape(shape);
        shape = shapeFactory.createShape(new Vector2f(60, 45), 80, 50, ShapeType.Triangle);
        mToolsList.addShape(shape);
    }

    private void initTools(Context context, int screenWidth) {
        int resizeValue = DEFAULT_BUTTON_RESIZE;
        Bitmap mBitmapTools;
        int x = screenWidth - DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
        int y = DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;
        Vector2f position = new Vector2f(x, y);
        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmaps.put(mBitmapTools, position);
        x += DEFAULT_SHIFT_FOR_TOOLBAR;
        position.set(x, y);

        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmaps.put(mBitmapTools, position);
        x += DEFAULT_SHIFT_FOR_TOOLBAR;
        position.set(x, y);

        mBitmapTools = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        mBitmapTools = PainterUtils.getResizedBitmap(mBitmapTools, resizeValue, resizeValue);
        mBitmaps.put(mBitmapTools, position);
    }

    public Map<Bitmap, Vector2f> getBitmaps() {
        return mBitmaps;
    }

    public ShapesList getTools() {
        return mToolsList;
    }
}
