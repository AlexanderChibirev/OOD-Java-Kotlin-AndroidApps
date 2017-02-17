package com.example.alexander.shapespaintermvp.ui.activities;

import android.os.Bundle;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.presenters.CanvasPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;
import com.example.alexander.shapespaintermvp.ui.views.PainterCanvas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasActivity extends MvpAppCompatActivity implements CanvasView {

    @InjectPresenter
    CanvasPresenter mCanvasPresenter;

    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        imageButtonCircle.setOnClickListener(view -> mCanvasPresenter.addShape(ShapeType.Ellipse));
        imageButtonRectangle.setOnClickListener(view -> mCanvasPresenter.addShape(ShapeType.Rectangle));
        imageButtonTriangle.setOnClickListener(view -> mCanvasPresenter.addShape(ShapeType.Triangle));
        imageButtonUndo.setOnClickListener(view -> mCanvasPresenter.undo());
        imageButtonRedo.setOnClickListener(view -> mCanvasPresenter.redo());
        imageButtonTrash.setOnClickListener(view -> mCanvasPresenter.trash());

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
    public void addShape(ShapeType shapeType) {

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
