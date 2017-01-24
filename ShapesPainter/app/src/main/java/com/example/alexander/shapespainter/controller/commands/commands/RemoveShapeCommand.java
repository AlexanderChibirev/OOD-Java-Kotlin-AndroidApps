package com.example.alexander.shapespainter.controller.commands.commands;

import com.example.alexander.shapespainter.ShapesList;
import com.example.alexander.shapespainter.controller.commands.ICommand;
import com.example.alexander.shapespainter.model.Shape;

public class RemoveShapeCommand implements ICommand {

    private ShapesList mPictureDraft;
    private Shape mShape;

    RemoveShapeCommand(ShapesList pictureDraft, Shape shape) {
        mPictureDraft = pictureDraft;
        mShape = shape;
    }
    @Override
    public void execute() {
        mPictureDraft.removeShape(mShape);
    }
}
