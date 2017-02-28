package com.example.alexander.shapespaintermvp.mvp.views;

import android.graphics.Canvas;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.ui.views.ShapeDiagram;

@StateStrategyType(AddToEndStrategy.class)
public interface CanvasView extends MvpView {

    void painterShapes(Canvas canvas, ShapesList shapesList);

    void painterShapeDiagram(ShapeDiagram shapeDiagram);

    void moveShape();

    void removeShape();

    void resizeShape();

}
