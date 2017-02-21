package com.example.alexander.shapespaintermvp.mvp.views;

import android.graphics.Canvas;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;

@StateStrategyType(AddToEndStrategy.class)
public interface CanvasView extends MvpView {

    void painterShapes(Canvas canvas, ShapesList shapesList);

    void painterShapeDiagram(Canvas canvas);

    void moveShape();

    void removeShape();

    void resizeShape();

}
