package com.example.alexander.shapespainter.controller.commands.commands;

import com.example.alexander.shapespainter.controller.commands.ICommand;
import com.example.alexander.shapespainter.model.Shape;

import javax.vecmath.Vector2f;

public class MoveShapeCommand implements ICommand {
    private Shape mShape;
    private Vector2f mToPoint;

    public MoveShapeCommand(Shape shape, Vector2f toPoint) {
        mShape = shape;
        mToPoint = toPoint;
    }

    @Override
    public void execute() {
        mShape.setCenter(mToPoint);
    }
}
