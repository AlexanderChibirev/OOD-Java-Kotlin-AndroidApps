package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import com.example.alexander.shapespainter.utils.PainterUtils;

import javax.vecmath.Vector2f;


public class PainterThread extends Thread implements ICanvas {

    private final SurfaceHolder mSurfaceHolder;
    private boolean mIsActive = false;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Painter mPainter = new Painter();

    private Bitmap bitmapIconRedo;
    private Bitmap bitmapIconUndo;
    private Bitmap bitmapIconTrash;

    private PictureDraft mPictureDraft;


    PainterThread(SurfaceHolder surfaceHolder, Context context, PictureDraft pictureDraft) {
        mPictureDraft = pictureDraft;
        initIcons(context);
        mPaint.setColor(Color.BLACK);
        mSurfaceHolder = surfaceHolder;
    }

    private void initIcons(Context context) {
        int resizeValue = 80;
        bitmapIconRedo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        bitmapIconRedo = PainterUtils.getResizedBitmap(bitmapIconRedo, resizeValue, resizeValue);
        bitmapIconUndo  = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        bitmapIconUndo = PainterUtils.getResizedBitmap(bitmapIconUndo, resizeValue, resizeValue);
        bitmapIconTrash = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        bitmapIconTrash = PainterUtils.getResizedBitmap(bitmapIconTrash, resizeValue, resizeValue);
    }

    public void setPictureDraft(PictureDraft pictureDraft) {
        mPictureDraft = pictureDraft;
    }

    void setRunning(boolean run) {
        mIsActive = run;
    }

    @Override
    public  void run() {
        waitForBitmap();
        Canvas canvas;
        while (mIsActive) {
            canvas = null;
            try {
                // получаем объект Canvas и выполняем отрисовку
                canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {
                    canvas.drawBitmap(mBitmap, 0, 0, null);
                    drawTools();
                    mPainter.drawPicture(mPictureDraft, this);
                }
            }
            finally {
                if (canvas != null) {
                    // отрисовка выполнена. выводим результат на экран
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawTools() {
        int x = 450;
        int y = 9;
        int shiftX = 100;
        mCanvas.drawBitmap(bitmapIconUndo, x, y, mPaint);
        x += shiftX;
        mCanvas.drawBitmap(bitmapIconRedo, x, y, mPaint);
        x += shiftX;
        mCanvas.drawBitmap(bitmapIconTrash, x, y, mPaint);
    }

    private void waitForBitmap() {
        while (mBitmap == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void setBitmap(Bitmap bitmap, boolean clear) {
        mBitmap = bitmap;
        if (clear) {
            mBitmap.eraseColor(Color.WHITE);
        }
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    public void drawRectangle(float left, float top, float right, float bottom) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect((int) left, (int) top, (int) right,(int) bottom);
        mCanvas.drawRect(rect, mPaint);
        //////////////////////////////////////////////////////////////////////
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawRect(rect, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float vRadius) {
        RectF rectangle = new RectF(center.x - hRadius, center.y - vRadius, center.x + hRadius, center.y + vRadius);
        System.out.print(rectangle);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawOval(rectangle, mPaint);
        //////////////////////////////////////////////////////////////////////
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawOval(rectangle, mPaint);
    }

    @Override
    public void drawTriangle(Vector2f vertex1, Vector2f vertex2, Vector2f vertex3) {
        PainterUtils.drawPoly(mCanvas, Color.BLACK,
                new Vector2f[]{
                        new Vector2f(vertex1.x, vertex1.y),
                        new Vector2f(vertex2.x, vertex2.y),
                        new Vector2f(vertex3.x, vertex3.y)
                });
        //////////////////////////////////////////////////////////////////////
        int shiftForOutlineColor = 1;
        PainterUtils.drawPoly(mCanvas, Color.BLUE,
                new Vector2f[]{
                        new Vector2f(vertex1.x + shiftForOutlineColor, vertex1.y - shiftForOutlineColor),
                        new Vector2f(vertex2.x, vertex2.y + shiftForOutlineColor),
                        new Vector2f(vertex3.x - shiftForOutlineColor, vertex3.y - shiftForOutlineColor)
                });
    }
}
