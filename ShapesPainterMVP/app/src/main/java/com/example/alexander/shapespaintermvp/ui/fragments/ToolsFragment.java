package com.example.alexander.shapespaintermvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.shapespaintermvp.R;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.presenters.ToolbarsPresenter;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolsFragment extends MvpAppCompatFragment implements ToolbarsView {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        return inflater.inflate(R.layout.fragment_toolbars, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        imageButtonCircle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Ellipse));
        imageButtonRectangle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Rectangle));
        imageButtonTriangle.setOnClickListener(imageButton -> mToolbarsPresenter.addShape(ShapeType.Triangle));
        imageButtonUndo.setOnClickListener(imageButton -> mToolbarsPresenter.undo());
        imageButtonRedo.setOnClickListener(imageButton -> mToolbarsPresenter.redo());
        imageButtonTrash.setOnClickListener(imageButton -> mToolbarsPresenter.trash());
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
}
