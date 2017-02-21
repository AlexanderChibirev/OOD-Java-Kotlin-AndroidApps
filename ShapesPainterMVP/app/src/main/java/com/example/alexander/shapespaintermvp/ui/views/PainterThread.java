package com.example.alexander.shapespaintermvp.ui.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class PainterThread extends Thread {
    private boolean running = false;
    private PainterCanvas mCanvas;
    private SurfaceHolder mSurfaceHolder;

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
                        mCanvas.updateShape();
                        mCanvas.painterShapes(canvas);
                        mCanvas.painterShapeDiagram(canvas);
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
