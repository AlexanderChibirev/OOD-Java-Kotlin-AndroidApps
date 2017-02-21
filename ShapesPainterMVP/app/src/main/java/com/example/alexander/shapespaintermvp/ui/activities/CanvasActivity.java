package com.example.alexander.shapespaintermvp.ui.activities;

import android.graphics.Canvas;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.presenters.CanvasPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;
import com.example.alexander.shapespaintermvp.ui.fragments.ToolsFragment;
import com.example.alexander.shapespaintermvp.ui.views.Painter;
import com.example.alexander.shapespaintermvp.ui.views.PainterCanvas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasActivity extends MvpAppCompatActivity implements CanvasView {

    @InjectPresenter
    CanvasPresenter mCanvasPresenter;

    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;

    private Painter mPainter = new Painter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFragment();
        mPainterCanvas.setCanvasPresenter(mCanvasPresenter);
    }

    @Override
    public void painterShapes(Canvas canvas, ShapesList shapesList) {
        mPainter.drawShapes(shapesList, canvas);
    }

    @Override
    public void painterShapeDiagram(Canvas canvas) {

    }

    @Override
    public void moveShape() {

    }

    @Override
    public void removeShape() {

    }

    @Override
    public void resizeShape() {

    }

    private void showFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_toolbars, ToolsFragment.getInstance(mPainterCanvas))
                .commit();
    }

}

