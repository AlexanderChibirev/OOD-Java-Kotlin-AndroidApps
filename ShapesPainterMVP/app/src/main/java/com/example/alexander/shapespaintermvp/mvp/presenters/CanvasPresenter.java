package com.example.alexander.shapespaintermvp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeFactory;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;


@InjectViewState
public class CanvasPresenter extends MvpPresenter<CanvasView> {
    private ShapeFactory mShapeFactory = new ShapeFactory();

    public void addShape(ShapeType shapeType) {
        getViewState().addShape(shapeType);
    }

    public void undo() {
        getViewState().undo();
    }

    public void redo() {
        getViewState().redo();
    }

    public void trash() {
        getViewState().trash();
    }

}
