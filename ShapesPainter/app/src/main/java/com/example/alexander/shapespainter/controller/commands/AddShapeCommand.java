package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.IShape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.ShapesList;

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
