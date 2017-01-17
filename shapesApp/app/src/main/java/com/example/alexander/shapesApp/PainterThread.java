package com.example.alexander.shapesApp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

/**
 * Created by Alexander on 17.01.2017.
 */

public class PainterThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private boolean mRunning = false;

    public PainterThread(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean run) {
        mRunning = run;
    }

    @Override
    public  void run() {
        Canvas canvas;
        while (mRunning) {
            canvas = null;
            try {
                // получаем объект Canvas и выполняем отрисовку
                canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {
                    canvas.drawColor(Color.WHITE);
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

}
