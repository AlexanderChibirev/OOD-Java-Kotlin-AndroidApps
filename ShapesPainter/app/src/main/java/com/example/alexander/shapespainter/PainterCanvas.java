package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap.CompressFormat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Alexander on 18.01.2017.
 */


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback  {

    private PainterThread mPainterThread;
    private Bitmap mBitmap;
    public PainterCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPainterThread = new PainterThread(getHolder());
        mPainterThread.setRunning(true);
        mPainterThread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mBitmap == null) {
            mBitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);//каждый пиксель хранит 4 байта
            mPainterThread.setBitmap(mBitmap, true);
            MainActivity mainActivity = (MainActivity) getContext();

            Bitmap bitmap = mainActivity.getLastPicture();
            if (bitmap != null) {
                mPainterThread.restoreBitmap(bitmap, new Matrix());
            }
        } else {
            mPainterThread.setBitmap(mBitmap, false);
        }
        mPainterThread.activate();
    }

    public void saveBitmap(String pictureName) throws FileNotFoundException {
        synchronized (getHolder()) {
            FileOutputStream fos = new FileOutputStream(pictureName);
            mPainterThread.getBitmap().compress(CompressFormat.PNG, 100, fos);
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

            }
        }
        mPainterThread = null;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!mPainterThread.isReady()) {

            return false;
        }
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
