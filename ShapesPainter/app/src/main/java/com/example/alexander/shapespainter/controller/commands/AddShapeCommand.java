package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.model.ShapesList;
import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.ConstWorld.SHAPE_DEFAULT_FOR_ELLIPSE_WIDTH;
import static com.example.alexander.shapespainter.constants.ConstWorld.SHAPE_DEFAULT_HEIGHT;
import static com.example.alexander.shapespainter.constants.ConstWorld.SHAPE_DEFAULT_POSITION;
import static com.example.alexander.shapespainter.constants.ConstWorld.SHAPE_DEFAULT_WIDTH;

public class AddShapeCommand implements ICommand {
    private ShapesList mShapesList;
    private Shape mShape;

    public AddShapeCommand(ShapesList shapesList, ShapeType shapeType) {
        mShapesList = shapesList;
        ShapeFactory shapeFactory = new ShapeFactory();
        if (shapeType == ShapeType.Ellipse) {
            mShape = shapeFactory.createShape(
                    new Vector2f(SHAPE_DEFAULT_POSITION, SHAPE_DEFAULT_POSITION),
                    SHAPE_DEFAULT_FOR_ELLIPSE_WIDTH,
                    SHAPE_DEFAULT_HEIGHT,
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
