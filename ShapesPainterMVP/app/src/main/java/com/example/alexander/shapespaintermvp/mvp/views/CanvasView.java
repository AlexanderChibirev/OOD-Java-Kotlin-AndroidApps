package com.example.alexander.shapespaintermvp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CanvasView extends MvpView {

    void painterShapes();

    void painterShapeDiagram();

    void redo();

    void undo();

    void trash();

    void addShape(ShapeType shapeType);

    void moveShape();

    void removeShape();

    void resizeShape();

}
