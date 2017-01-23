package com.example.alexander.shapespainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.alexander.shapespainter.utils.PainterUtils;

import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
import static com.example.alexander.shapespainter.ConstWorld.DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;


public class PainterCanvas extends SurfaceView implements SurfaceHolder.Callback  {

    private PainterThread mPainterThread = null;
    private Paint mPaint = new Paint();

    private Bitmap mBitmapIconRedo;
    private Bitmap mBitmapIconUndo;
    private Bitmap mBitmapIconTrash;

    private int mScreenWidth;

    public PainterCanvas(Context context, int screenWidth) {
        super(context);
        getHolder().addCallback(this);
        initIcons(context);
        mScreenWidth = screenWidth;
    }

    public void startApp()
    {
        if (mPainterThread == null)
        {
            mPainterThread = new PainterThread(this);
            mPainterThread.startThread();
        }
    }

    public void stopApp()
    {
        if (mPainterThread != null)
        {
            mPainterThread.stopThread();

            // Waiting for the mPainterThread to die by calling mPainterThread.join,
            // repeatedly if necessary
            boolean retry = true;
            while (retry)
            {
                try
                {
                    mPainterThread.join();
                    retry = false;
                }
                catch (InterruptedException e)
                {
                }
            }
            mPainterThread = null;
        }
    }


    public void updateModels()
    {
        //TODO:: обновляем контроллер
        //circle.checkBounds(getWidth(), getHeight());
        //circle.move();
    }

    public void drawModels(Canvas canvas)
    {
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(1, 1, 100, 100, mPaint);
        //TODO:: рисуем холст
        //canvas.drawColor(Color.WHITE);
        //circle.draw(canvas);
    }

    public void drawTools(Canvas canvas) {
        int x = mScreenWidth - DEFAULT_SHIFT_FOR_START_TOOLBAR_X;
        int y = DEFAULT_SHIFT_FOR_START_TOOLBAR_Y;
        canvas.drawBitmap(mBitmapIconUndo, x, y, mPaint);
        x += DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
        canvas.drawBitmap(mBitmapIconRedo, x, y, mPaint);
        x += DEFAULT_SHIFT_FOR_MOVING_TOOLBAR;
        canvas.drawBitmap(mBitmapIconTrash, x, y, mPaint);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        startApp();
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        stopApp();
    }

    private void initIcons(Context context) {
        int resizeValue = 80;
        mBitmapIconRedo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_redo);
        mBitmapIconRedo = PainterUtils.getResizedBitmap(mBitmapIconRedo, resizeValue, resizeValue);
        mBitmapIconUndo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_undo);
        mBitmapIconUndo = PainterUtils.getResizedBitmap(mBitmapIconUndo, resizeValue, resizeValue);
        mBitmapIconTrash = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_trash);
        mBitmapIconTrash = PainterUtils.getResizedBitmap(mBitmapIconTrash, resizeValue, resizeValue);
    }


}
