package com.example.alexander.shapespaintermvp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndStrategy.class)
public interface CanvasView extends MvpView {

    void painterShapes();

    void painterShapeDiagram();

    void moveShape();

    void removeShape();

    void resizeShape();

}
