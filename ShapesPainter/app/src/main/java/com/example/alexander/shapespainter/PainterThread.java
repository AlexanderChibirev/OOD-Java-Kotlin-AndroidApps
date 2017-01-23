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

import com.example.alexander.shapespainter.controller.commands.Designer;
import com.example.alexander.shapespainter.utils.PainterUtils;

import javax.vecmath.Vector2f;


class PainterThread extends Thread implements ICanvas {

    private final SurfaceHolder mSurfaceHolder;
    private boolean mIsActive = false;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Painter mPainter = new Painter();

    private Bitmap mBitmapIconRedo;
    private Bitmap mBitmapIconUndo;
    private Bitmap mBitmapIconTrash;

    private Designer mDesigner = new Designer();
    private PictureDraft mPictureDraft = mDesigner.createDraft();

    //////////////////TODO:: для отображения количества фигур в массиве
    private Paint fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    PainterThread(SurfaceHolder surfaceHolder, Context context) {
        initIcons(context);
        mPaint.setColor(Color.BLACK);
        mSurfaceHolder = surfaceHolder;
    }

    private void initIcons(Context context) {
        int resizeValue = 80;
        mBitmapIconRedo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        mBitmapIconRedo = PainterUtils.getResizedBitmap(mBitmapIconRedo, resizeValue, resizeValue);
        mBitmapIconUndo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        mBitmapIconUndo = PainterUtils.getResizedBitmap(mBitmapIconUndo, resizeValue, resizeValue);
        mBitmapIconTrash = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        mBitmapIconTrash = PainterUtils.getResizedBitmap(mBitmapIconTrash, resizeValue, resizeValue);
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
                    mDesigner.update();
                    canvas.drawBitmap(mBitmap, 0, 0, null);
                    drawTools();
                    mPainter.drawPicture(mPictureDraft, this);

                    ////////////////////////////////TODO:: для отображения количества фигур в массиве
                    fontPaint.setTextSize(200);
                    fontPaint.setStyle(Paint.Style.STROKE);
                    mCanvas.drawText(String.valueOf(mPictureDraft.getShapeCount()),10,100,fontPaint);
                    ////////////////////////////////////////////////////////////////////////////////
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
        mCanvas.drawBitmap(mBitmapIconUndo, x, y, mPaint);
        x += shiftX;
        mCanvas.drawBitmap(mBitmapIconRedo, x, y, mPaint);
        x += shiftX;
        mCanvas.drawBitmap(mBitmapIconTrash, x, y, mPaint);
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
    public void drawRectangle(Vector2f leftTop, Vector2f topBottom) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect((int) leftTop.x, (int) leftTop.y, (int) topBottom.x, (int) topBottom.y);
        mCanvas.drawRect(rect, mPaint);
        //////////////////////////////////////////////////////////////////////
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawRect(rect, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float vRadius, float hRadius) {
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

    void setMousePos(float x, float y) {
        mDesigner.setMousePos(x, y);
    }
}
