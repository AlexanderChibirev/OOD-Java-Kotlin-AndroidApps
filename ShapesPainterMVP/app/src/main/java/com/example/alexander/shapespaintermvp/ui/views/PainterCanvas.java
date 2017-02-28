package com.example.alexander.shapespaintermvp.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder = null;

    public PainterCanvas(Context context) {
        super(context);
        initCanvas();
    }

    public PainterCanvas(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        initCanvas();
    }


    public PainterCanvas(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCanvas();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas;
        canvas = mSurfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void initCanvas() {
        getHolder().addCallback(this);
        mSurfaceHolder = getHolder();
    }

    public Canvas getCanvas() {
        return mSurfaceHolder.lockCanvas();
    }

    public void unlockCanvasAndPost(Canvas canvas) {
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }
}
