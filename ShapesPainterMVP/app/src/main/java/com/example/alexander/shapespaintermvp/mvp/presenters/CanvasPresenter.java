package com.example.alexander.shapespaintermvp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeFactory;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;


@InjectViewState
public class CanvasPresenter extends MvpPresenter<CanvasView> {
    private ShapeFactory mShapeFactory = new ShapeFactory();

}
