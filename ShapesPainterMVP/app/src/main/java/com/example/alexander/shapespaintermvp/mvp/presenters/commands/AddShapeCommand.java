package com.example.alexander.shapespaintermvp.mvp.presenters.commands;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeFactory;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;

public class AddShapeCommand implements ICommand {
    private ShapesList mShapesList;
    private IShape mShape;

    public AddShapeCommand(ShapesList shapesList, ShapeType shapeType, ShapeFactory shapeFactory) {
        mShapesList = shapesList;
        mShape = shapeFactory.createDefaultShape(shapeType);
    }

    @Override
    public void execute() {
        mShapesList.addShape(mShape);
    }

    @Override
    public void unExecute() {
        mShapesList.removeShape(mShape);
    }
}
