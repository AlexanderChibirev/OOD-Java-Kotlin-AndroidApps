package com.example.alexander.shapespainter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.alexander.shapespainter.controller.Controller;
import com.example.alexander.shapespainter.controller.MouseActionType;

import javax.vecmath.Vector2f;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback {

    private PainterThread mPainterThread = null;
    private Painter mPainter;
    private Controller mController;
    private Context mContext;

    public PainterCanvas(Context context, int screenWidth) {
        super(context);
        getHolder().addCallback(this);
        mController = new Controller(context, screenWidth);
        mPainter = new Painter();
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
            // Waiting for the mPainterThread to die by calling mPainterThread.join,
            // repeatedly if necessary
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

    public void drawModels(Canvas canvas) {
        mPainter.drawPicture(mController.getShapesDraft(), canvas);
        if (mController.getSelectDiagramShape().getShape() != null) {
            mPainter.drawSelectDiagramShape(mController.getSelectDiagramShape(), canvas);
        }
    }

    public void drawTools(Canvas canvas) {
        mPainter.drawTools(canvas, mController.getToolsDraft(), mController.getBitmapTools());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        startApp();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopApp();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Vector2f mousePos = new Vector2f(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://нажатие
                mController.setMouseMotionType(MouseActionType.Down);
                mController.updateToolbars(mousePos);
                mController.updateShapes(mousePos);
                break;
            case MotionEvent.ACTION_MOVE://движение
                mController.setMouseMotionType(MouseActionType.Move);
                mController.updateShapes(mousePos);
                break;
            case MotionEvent.ACTION_UP: // отпускание
                mController.setMouseMotionType(MouseActionType.Up);
                mController.updateShapes(mousePos);
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        invalidate();
        return true;
    }


}

