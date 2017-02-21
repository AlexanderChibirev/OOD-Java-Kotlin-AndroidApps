package com.example.alexander.shapespaintermvp.mvp.presenters.commands;


import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;

public class RemoveShapeCommand implements ICommand {
    private ShapesList mShapesList;
    private IShape mShape;
    private int mIndex;

    public RemoveShapeCommand(ShapesList shapeList, IShape shape) {
        mShapesList = shapeList;
        mShape = shape;
        mIndex = shapeList.getIndexShape(shape);
    }

    @Override
    public void execute() {
        mShapesList.removeShape(mShape);
    }

    @Override
    public void unExecute() {
        mShapesList.insertShape(mIndex, mShape);
    }
}
