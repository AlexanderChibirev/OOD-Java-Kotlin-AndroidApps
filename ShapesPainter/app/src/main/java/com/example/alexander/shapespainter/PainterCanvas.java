package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.alexander.shapespainter.controller.commands.Designer;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback  {


    private PainterThread mPainterThread;
    private Bitmap mBitmap;
    private Designer mDesigner = new Designer();

    public PainterCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        PictureDraft pictureDraft = mDesigner.createDraft();
        mPainterThread = new PainterThread(getHolder(), getContext(), pictureDraft);
        mPainterThread.setRunning(true);
        mPainterThread.start();
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
}
