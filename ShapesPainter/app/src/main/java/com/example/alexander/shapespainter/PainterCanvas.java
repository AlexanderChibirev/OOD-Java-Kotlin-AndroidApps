package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.alexander.shapespainter.controller.commands.Controller;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback {

    private PainterThread mPainterThread = null;
    private Paint mPaint = new Paint();

    private int x = 0;
    private int mScreenWidth;
    private Painter mPainter;
    private ShapesList mShapesList = new ShapesList();
    private Controller mController;

    public PainterCanvas(Context context, int screenWidth) {
        super(context);
        getHolder().addCallback(this);
        mController = new Controller(context, screenWidth);
        mPainter = new Painter(context);
        mScreenWidth = screenWidth;
    }

    public void startApp() {
        if (mPainterThread == null) {
            mPainterThread = new PainterThread(this);
            mPainterThread.startThread();
        }
    }

    public void stopApp() {
        if (mPainterThread != null) {
            mPainterThread.stopThread();

            // Waiting for the mPainterThread to die by calling mPainterThread.join,
            // repeatedly if necessary
            boolean retry = true;
            while (retry) {
                try {
                    mPainterThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mPainterThread = null;
        }
    }

    public void updateModels() {
        //TODO:: обновляем контроллер
        //circle.checkBounds(getWidth(), getHeight());
        //circle.move();
    }

    public void drawModels(Canvas canvas) {
        mPainter.drawPicture(mController.getShapesDraft(), canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(x, 1, 100, 100, mPaint);
        x++;
        //TODO:: рисуем холст
        //canvas.drawColor(Color.WHITE);
        //circle.draw(canvas);
    }

    public void drawTools(Canvas canvas) {
        mPainter.drawTools(canvas, mController.getToolsDraft(), mController.getBitmaps());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        startApp();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopApp();
    }


}
