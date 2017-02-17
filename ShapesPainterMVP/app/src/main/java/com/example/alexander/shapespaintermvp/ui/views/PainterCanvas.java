package com.example.alexander.shapespaintermvp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback {
    private PainterThread mPainterThread;

    public PainterCanvas(Context context) {
        super(context);
    }

    public PainterCanvas(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }


    public PainterCanvas(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startApp();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopApp();
    }

    public void updateShape() {

    }

    public void draw() {

    }
}
