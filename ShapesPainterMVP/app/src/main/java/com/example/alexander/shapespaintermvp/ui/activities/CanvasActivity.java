package com.example.alexander.shapespaintermvp.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.presenters.CanvasPresenter;
import com.example.alexander.shapespaintermvp.mvp.presenters.ToolbarsPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;
import com.example.alexander.shapespaintermvp.ui.views.Painter;
import com.example.alexander.shapespaintermvp.ui.views.PainterCanvas;
import com.example.alexander.shapespaintermvp.ui.views.ShapeDiagram;

import javax.vecmath.Vector2f;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasActivity extends MvpAppCompatActivity implements CanvasView, ToolbarsView {

    @InjectPresenter
    CanvasPresenter mCanvasPresenter;
    @InjectPresenter
    ToolbarsPresenter mToolbarPresenter;

    @BindView(R.id.imageButtonCircle)
    ImageButton imageButtonCircle;
    @BindView(R.id.imageButtonRectangle)
    ImageButton imageButtonRectangle;
    @BindView(R.id.imageButtonTriangle)
    ImageButton imageButtonTriangle;
    @BindView(R.id.imageButtonUndo)
    ImageButton imageButtonUndo;
    @BindView(R.id.imageButtonRedo)
    ImageButton imageButtonRedo;
    @BindView(R.id.imageButtonTrash)
    ImageButton imageButtonTrash;
    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;

    private ShapesList mShapesList = new ShapesList();
    private Painter mPainter = new Painter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this);
        initButtonsToolbar();
    }

    @Override
    public void painterShapes(Canvas canvas, ShapesList shapesList) {
        mPainter.drawShapes(shapesList, canvas);
        mPainterCanvas.unlockCanvasAndPost(canvas);
    }

    @Override
    public void painterShapeDiagram(ShapeDiagram shapeDiagram) {
        if (shapeDiagram == null) {
            mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
        }
        else {
            Canvas canvas = mPainterCanvas.getCanvas();
            mPainter.drawSelectDiagramShape(shapeDiagram, canvas);
            mPainterCanvas.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void moveShape() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void removeShape() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void resizeShape() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void redo() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void undo() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void trash() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void addShape() {
        mCanvasPresenter.painterShapes(mPainterCanvas.getCanvas(), mShapesList);
    }

    @Override
    public void showMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Vector2f mousePos = new Vector2f(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCanvasPresenter.down(mousePos, mShapesList.getShapes());
                break;
            case MotionEvent.ACTION_MOVE:
                mCanvasPresenter.move(mousePos, mShapesList.getShapes());
                break;
            case MotionEvent.ACTION_UP:
                mCanvasPresenter.up(mousePos, mShapesList.getShapes());
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        createDialogExit();
    }

    private void createDialogExit() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                CanvasActivity.this);
        quitDialog.setTitle(R.string.exit_app_prompt);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCanvasPresenter.saveStateShape(mShapesList, getApplicationContext());
                System.exit(0);
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        quitDialog.show();
    }


    private void initButtonsToolbar() {
        imageButtonCircle.setOnClickListener(imageButton -> mToolbarPresenter.addShape(ShapeType.Ellipse, mShapesList));
        imageButtonRectangle.setOnClickListener(imageButton -> mToolbarPresenter.addShape(ShapeType.Rectangle, mShapesList));
        imageButtonTriangle.setOnClickListener(imageButton -> mToolbarPresenter.addShape(ShapeType.Triangle, mShapesList));
        imageButtonUndo.setOnClickListener(imageButton -> mToolbarPresenter.undo());
        imageButtonRedo.setOnClickListener(imageButton -> mToolbarPresenter.redo());
        imageButtonTrash.setOnClickListener(imageButton -> mToolbarPresenter.trash());
    }
}

