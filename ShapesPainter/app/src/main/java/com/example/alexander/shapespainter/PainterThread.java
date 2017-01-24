package com.example.alexander.shapespainter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

class PainterThread extends Thread {
    private final static int SLEEP_TIME = 1;

    private boolean running = false;
    private PainterCanvas mCanvas = null;
    private SurfaceHolder mSurfaceHolder = null;

    PainterThread(PainterCanvas canvas) {
        super();
        this.mCanvas = canvas;
        this.mSurfaceHolder = canvas.getHolder();
    }

    void startThread() {
        running = true;
        super.start();
    }

    void stopThread() {
        running = false;
    }

    public void run() {
        Canvas canvas = null;
        while (running) {
            canvas = null;
            try {
                canvas = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    if (canvas != null) {
                        canvas.drawColor(Color.WHITE);
                        mCanvas.updateModels();
                        mCanvas.drawModels(canvas);
                        mCanvas.drawTools(canvas);
                    }
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } finally {
                if (canvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
