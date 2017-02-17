package com.example.alexander.shapespaintermvp.mvp.views;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;

@StateStrategyType(AddToEndStrategy.class)
public interface ToolbarsView extends MvpView {

    void redo();

    void undo();

    void trash();

    void addShape(ShapeType shapeType);
}
