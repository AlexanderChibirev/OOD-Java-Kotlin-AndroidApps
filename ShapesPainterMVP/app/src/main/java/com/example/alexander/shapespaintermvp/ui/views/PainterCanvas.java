package com.example.alexander.shapespaintermvp.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.presenters.CanvasPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;

import java.io.Serializable;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback, Serializable, ToolbarsView {
    private PainterThread mPainterThread;
    private ShapesList mShapesList = new ShapesList();
    private CanvasPresenter mCanvasPresenter;

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

    public void setCanvasPresenter(CanvasPresenter canvasPresenter) {
        mCanvasPresenter = canvasPresenter;
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

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void trash() {

    }

    @Override
    public void addShape(IShape shape) {
        mShapesList.addShape(shape);
    }

    @Override
    public void showMessage(String message) {

    }

    public void painterShapes(Canvas canvas) {
        mCanvasPresenter.painterShapes(canvas, mShapesList);
    }

    public void painterShapeDiagram(Canvas canvas) {

    }
}
