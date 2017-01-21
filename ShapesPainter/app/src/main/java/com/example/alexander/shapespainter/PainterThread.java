package com.example.alexander.shapespainter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import javax.vecmath.Vector2f;


class PainterThread extends Thread implements ICanvas {
    private final int SLEEP = 0;
    private final int READY = 1;

    private final SurfaceHolder mSurfaceHolder;
    private boolean mIsActive = false;
    private Bitmap mBitmap;
    private int mCanvasBgColor = Color.WHITE;
    private Canvas mCanvas;
    private State mState;
    private int mStatus;
    private  Paint paint= new Paint();

    PainterThread(SurfaceHolder surfaceHolder) {
        paint.setColor(Color.BLACK);
        mSurfaceHolder = surfaceHolder;
    }

    void setRunning(boolean run) {
        mIsActive = run;
    }

    Bitmap getBitmap() {
        return mBitmap;
    }

    void restoreBitmap(Bitmap bitmap, Matrix matrix) {
        mCanvas.drawBitmap(bitmap, matrix, new Paint(Paint.FILTER_BITMAP_FLAG));
        //TODO:: добавить эту строку после создания redo undo mState.undoBuffer = saveBuffer();
    }

    public void freeze() {
        mStatus = SLEEP;
    }

    void activate() {
        mStatus = READY;
    }

    boolean isFreeze() {
        return (mStatus == SLEEP);
    }

    boolean isReady() {
        return (mStatus == READY);
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
                    draw(canvas);
                }
            }
            finally {
                if (canvas != null) {
                    // отрисовка выполнена. выводим результат на экран
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
                if (isFreeze()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void draw(Canvas canvas) {
        switch (mStatus) {
            case READY: {
                if (canvas != null) {// drawing shape
                    canvas.drawBitmap(mBitmap, 0, 0, null);
                    //canvas.drawText("hey", 100, 25, paint);//x= 100, y=25
                                /*p.setStyle(Paint.Style.STROKE);
                                rect.offset(150, 0);
                                canvas.drawRect(rect, p);*/
                }
                break;
            }
        }
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
            mBitmap.eraseColor(mCanvasBgColor);
        }
        mCanvas = new Canvas(mBitmap);
    }

/*   TODO:: добавить, когда будет готов класс сохранения
        private byte[] saveBuffer() {
        byte[] buffer = new byte[mBitmap.getRowBytes() * mBitmap.getHeight()];
        Buffer byteBuffer = ByteBuffer.wrap(buffer);
        mBitmap.copyPixelsToBuffer(byteBuffer);
        return buffer;
    }*/

    @Override
    public void setFillColor(Color color) {

    }

    @Override
    public void setOutlineColor(Color color) {

    }

    @Override
    public void setCenter(Vector2f pos) {

    }

    @Override
    public void setSize(float width, float height) {

    }

    @Override
    public void drawRectangle(Vector2f leftTop, float width, float height, Color fillColor, Color outlineColor) {

    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float vRadius, Color fillColor, Color outlineColor) {

    }

    @Override
    public void drawTriangle(Vector2f leftPoint, Vector2f rightPoint, Vector2f topPoint, Color fillColor, Color outlineColor) {

    }
}
