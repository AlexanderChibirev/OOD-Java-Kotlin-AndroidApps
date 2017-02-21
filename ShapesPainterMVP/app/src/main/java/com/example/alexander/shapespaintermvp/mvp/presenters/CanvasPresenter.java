package com.example.alexander.shapespaintermvp.mvp.presenters;

import android.graphics.Canvas;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;


@InjectViewState
public class CanvasPresenter extends MvpPresenter<CanvasView> {

    public void painterShapes(Canvas canvas, ShapesList shapesList) {
        getViewState().painterShapes(canvas, shapesList);
    }
}
