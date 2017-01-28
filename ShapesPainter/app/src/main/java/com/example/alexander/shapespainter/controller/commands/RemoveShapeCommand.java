package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.model.ShapesList;
import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.Shape;

public class RemoveShapeCommand implements ICommand {
    private ShapesList mShapesList;
    private Shape mShape;
    private int mIndex;

    public RemoveShapeCommand(ShapesList shapeList, Shape shape) {
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
