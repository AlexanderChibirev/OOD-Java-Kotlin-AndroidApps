package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.Shape;

import javax.vecmath.Vector2f;

public class MoveShapeCommand implements ICommand {
    private Shape mShape;
    private Vector2f mToPoint;
    private Vector2f mFromPoint;

    public MoveShapeCommand(Shape shape, Vector2f toPoint, Vector2f fromPoint) {
        mShape = shape;
        mFromPoint = fromPoint;
        mToPoint = toPoint;
    }

    @Override
    public void execute() {
        mShape.setCenter(mToPoint);
    }

    @Override
    public void unExecute() {
        mShape.setCenter(mFromPoint);
    }
}
