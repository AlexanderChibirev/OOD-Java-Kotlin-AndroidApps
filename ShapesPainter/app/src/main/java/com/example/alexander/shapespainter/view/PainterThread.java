package com.example.alexander.shapespainter.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

class PainterThread extends Thread {
    private boolean running = false;
    private PainterCanvas mCanvas = null;
    private SurfaceHolder mSurfaceHolder = null;

    PainterThread(PainterCanvas canvas) {
        super();
        this.mCanvas = canvas;
        this.mSurfaceHolder = canvas.getHolder();
    }

    public void startThread() {
        running = true;
        super.start();
    }


    public void stopThread() {
        running = false;
    }

    public void freezeThread() {
        stopThread();
    }

    public void activateThread() {
        running = true;
        run();
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;
            try {
                canvas = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    if (canvas != null) {
                        canvas.drawColor(Color.WHITE);
                        mCanvas.drawModels(canvas);
                        break;
                    }
                }
            } finally {
                if (canvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
