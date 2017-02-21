package com.example.alexander.shapespaintermvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.presenters.ToolbarsPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;
import com.example.alexander.shapespaintermvp.ui.views.PainterCanvas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolsFragment extends MvpAppCompatFragment implements ToolbarsView {
    public static final String ARGS_PAINTER_CANVAS = "argsPainterCanvas";

    @InjectPresenter
    ToolbarsPresenter mToolbarsPresenter;

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

    private PainterCanvas mPainterCanvas;
    private ShapesList mShapesList = new ShapesList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        mPainterCanvas = (PainterCanvas) getArguments().getSerializable(ARGS_PAINTER_CANVAS);
        return inflater.inflate(R.layout.fragment_toolbars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        imageButtonCircle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Ellipse, mShapesList));
        imageButtonRectangle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Rectangle, mShapesList));
        imageButtonTriangle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Triangle, mShapesList));
        imageButtonUndo.setOnClickListener(imageButton -> mToolbarsPresenter.undo());
        imageButtonRedo.setOnClickListener(imageButton -> mToolbarsPresenter.redo());
        imageButtonTrash.setOnClickListener(imageButton -> mToolbarsPresenter.trash());
    }

    @Override
    public void redo() {
        //отменяем выделение фигуры
        mPainterCanvas.redo();
    }

    @Override
    public void undo() {
        //отменяем выделение фигуры
    }

    @Override
    public void trash() {
        //отменяем выделение фигуры и удаляем ее из рисования
    }

    @Override
    public void showMessage(String message) {
        Toast toast = Toast.makeText(getContext().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void addShape(IShape shape) {
        mPainterCanvas.addShape(shape);
    }

    public static ToolsFragment getInstance(PainterCanvas painterCanvas) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_PAINTER_CANVAS, painterCanvas);

        ToolsFragment fragment = new ToolsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
