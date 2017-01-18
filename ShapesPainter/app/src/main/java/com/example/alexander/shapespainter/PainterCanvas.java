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
    private boolean mIsSetup;
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
                float bitmapWidth = bitmap.getWidth();
                float bitmapHeight = bitmap.getHeight();
                float scale = 1.0f;

                Matrix matrix = new Matrix();
               /* if (width != bitmapWidth || height != bitmapHeight) {
                    if (width == bitmapHeight || height == bitmapWidth) {
                        if (width > height) {
                            matrix.postRotate(-90, width / 2, height / 2);
                        } else if (bitmapWidth != bitmapHeight) {
                            matrix.postRotate(90, width / 2, height / 2);
                        } else {
                            if (painter.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                                matrix.postRotate(-90, width / 2, height / 2);
                            }
                        }
                    } else {
                        if (painter.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                            if (bitmapWidth > bitmapHeight
                                    && bitmapWidth > width) {
                                scale = (float) width / bitmapWidth;
                            } else if (bitmapHeight > bitmapWidth
                                    && bitmapHeight > height) {
                                scale = (float) height / bitmapHeight;
                            }
                        } else {
                            if (bitmapHeight > bitmapWidth
                                    && bitmapHeight > height) {
                                scale = (float) height / bitmapHeight;
                            } else if (bitmapWidth > bitmapHeight
                                    && bitmapWidth > width) {
                                scale = (float) width / bitmapWidth;
                            }
                        }
                    }

                    if (scale == 1.0f) {
                        matrix.preTranslate((width - bitmapWidth) / 2,
                                (height - bitmapHeight) / 2);
                    } else {
                        matrix.postScale(scale, scale, bitmapWidth / 2,
                                bitmapHeight / 2);
                        matrix.postTranslate((width - bitmapWidth) / 2,
                                (height - bitmapHeight) / 2);
                    }
                }*/
                mPainterThread.restoreBitmap(bitmap, matrix);
            }
        } else {
            mPainterThread.setBitmap(mBitmap, false);
        }

        if (!isSetup()) {
            mPainterThread.activate();
        } else {
            mPainterThread.setup();
        }
    }

    public void saveBitmap(String pictureName) throws FileNotFoundException {
        synchronized (getHolder()) {
            FileOutputStream fos = new FileOutputStream(pictureName);
            mPainterThread.getBitmap().compress(CompressFormat.PNG, 100, fos);
        }
    }

    public boolean isSetup() {
        return mIsSetup;
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
