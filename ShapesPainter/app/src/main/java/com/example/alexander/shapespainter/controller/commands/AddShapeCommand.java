package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.ShapesList;

import javax.vecmath.Vector2f;

public class AddShapeCommand implements ICommand {
    private final static float SHAPE_DEFAULT_WIDTH = 110;
    private final static float SHAPE_DEFAULT_HEIGHT = 80;
    private final static float SHAPE_DEFAULT_SIZE_FOR_CIRCLE = 80;
    private final static float SHAPE_DEFAULT_POSITION = 250f;
    private ShapesList mShapesList;
    private Shape mShape;

    public AddShapeCommand(ShapesList shapesList, ShapeType shapeType) {
        mShapesList = shapesList;
        ShapeFactory shapeFactory = new ShapeFactory();
        if (shapeType == ShapeType.Ellipse) {
            mShape = shapeFactory.createShape(
                    new Vector2f(SHAPE_DEFAULT_POSITION, SHAPE_DEFAULT_POSITION),
                    SHAPE_DEFAULT_SIZE_FOR_CIRCLE,
                    SHAPE_DEFAULT_SIZE_FOR_CIRCLE,
                    shapeType);
        } else {
            mShape = shapeFactory.createShape(
                    new Vector2f(SHAPE_DEFAULT_POSITION, SHAPE_DEFAULT_POSITION),
                    SHAPE_DEFAULT_WIDTH,
                    SHAPE_DEFAULT_HEIGHT,
                    shapeType);
        }
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
