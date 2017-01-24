package com.example.alexander.shapespainter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.utils.PainterUtils;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_BUTTON_RESIZE;
import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;

class Painter implements ICanvas {
    private Paint mPaint = new Paint();

    private Bitmap mBitmapIconRedo;
    private Bitmap mBitmapIconUndo;
    private Bitmap mBitmapIconTrash;

    void drawPicture(PictureDraft draft, Canvas canvas) {
        for (Shape shape : draft.getShapes()) {
            switch (shape.getType()) {
                case Ellipse:
                    break;
                case Rectangle:
                    break;
                case Triangle:
                    break;
            }
        }
    }

    Painter(Context context) {
        initTools(context);
    }

    @Override
    public void drawRectangle(Vector2f leftTop, Vector2f topBottom, Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect((int) leftTop.x, (int) leftTop.y, (int) topBottom.x, (int) topBottom.y);
        canvas.drawRect(rect, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float vRadius, Canvas canvas) {
        RectF rectangle = new RectF(center.x - hRadius, center.y - vRadius, center.x + hRadius, center.y + vRadius);
        System.out.print(rectangle);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangle, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangle, mPaint);
    }

    @Override
    public void drawTriangle(Vector2f vertex1, Vector2f vertex2, Vector2f vertex3, Canvas canvas) {
        PainterUtils.drawPoly(canvas, Color.BLACK,
                new Vector2f[]{
                        new Vector2f(vertex1.x, vertex1.y),
                        new Vector2f(vertex2.x, vertex2.y),
                        new Vector2f(vertex3.x, vertex3.y)
                });

        int shiftForOutlineColor = 1;
        PainterUtils.drawPoly(canvas, Color.BLUE,
                new Vector2f[]{
                        new Vector2f(vertex1.x + shiftForOutlineColor, vertex1.y - shiftForOutlineColor),
                        new Vector2f(vertex2.x, vertex2.y + shiftForOutlineColor),
                        new Vector2f(vertex3.x - shiftForOutlineColor, vertex3.y - shiftForOutlineColor)
                });
    }

    private void initTools(Context context) {
        int resizeValue = DEFAULT_BUTTON_RESIZE;
        mBitmapIconRedo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        mBitmapIconRedo = PainterUtils.getResizedBitmap(mBitmapIconRedo, resizeValue, resizeValue);
        mBitmapIconUndo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        mBitmapIconUndo = PainterUtils.getResizedBitmap(mBitmapIconUndo, resizeValue, resizeValue);
        mBitmapIconTrash = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        mBitmapIconTrash = PainterUtils.getResizedBitmap(mBitmapIconTrash, resizeValue, resizeValue);
    }

    void drawTools(Canvas canvas, int screenWidth) {
        int x = screenWidth - DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
        int y = DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;
        canvas.drawBitmap(mBitmapIconUndo, x, y, mPaint);
        x += DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
        canvas.drawBitmap(mBitmapIconRedo, x, y, mPaint);
        x += DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
        canvas.drawBitmap(mBitmapIconTrash, x, y, mPaint);
    }
}
