package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback  {

    private PainterThread mPainterThread;
    private Bitmap mBitmap;



    public PainterCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPainterThread = new PainterThread(getHolder(), getContext());
        mPainterThread.setRunning(true);
        mPainterThread.start();

       // mDesigner.setPainterThread(mPainterThread);
       // mDesigner.setRunning(true);
       // mDesigner.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mBitmap == null) {
            mBitmap = Bitmap.createBitmap(
                    width,
                    height,
                    Bitmap.Config.ARGB_8888);//каждый пиксель хранит 4 байта
            mPainterThread.setBitmap(mBitmap, true);
        } else {
            mPainterThread.setBitmap(mBitmap, false);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mPainterThread.setRunning(false);
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

    public boolean onTouchEvent(MotionEvent event) {
        mPainterThread.setMousePos(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }
}
