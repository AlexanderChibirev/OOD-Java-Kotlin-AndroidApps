package com.example.alexander.shapespaintermvp.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.presenters.CanvasPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;
import com.example.alexander.shapespaintermvp.ui.views.PainterCanvas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasActivity extends MvpAppCompatActivity implements CanvasView {

    @InjectPresenter()
    CanvasPresenter mCanvasPresenter;

    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

    @Override
    public void painterShapes() {

    }

    @Override
    public void painterShapeDiagram() {

    }

}
