package com.example.alexander.shapespainter.controller.commands.commands;

import com.example.alexander.shapespainter.controller.commands.ICommand;
import com.example.alexander.shapespainter.model.Shape;

import javax.vecmath.Vector2f;

public class MoveShapeCommand implements ICommand {
    private Shape mShape;
    private Vector2f mToPoint;
    private Vector2f mFromPoint;

    public MoveShapeCommand(Shape shape, Vector2f toPoint) {
        mShape = shape;
        mFromPoint = shape.getCenter();
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
