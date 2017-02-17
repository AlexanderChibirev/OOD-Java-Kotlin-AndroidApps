package com.example.alexander.shapespaintermvp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.views.ToolbarsView;

@InjectViewState
public class ToolbarsPresenter extends MvpPresenter<ToolbarsView> {

    public void redo() {
        getViewState().redo();
    }

    public void undo() {
        getViewState().undo();
    }

    public void trash() {
        getViewState().trash();
    }

    public void addShape(ShapeType shapeType) {
        getViewState().addShape(shapeType);
    }
}
