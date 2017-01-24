package com.example.alexander.shapespainter.controller.commands.commands;

import com.example.alexander.shapespainter.ShapesList;
import com.example.alexander.shapespainter.controller.commands.ICommand;
import com.example.alexander.shapespainter.model.Shape;

public class AddShapeCommand implements ICommand {
    private ShapesList mPictureDraft;
    private Shape mShape;

    public AddShapeCommand(ShapesList pictureDraft, Shape shape) {
        mPictureDraft = pictureDraft;
        mShape = shape;
    }

    @Override
    public void execute() {
        mPictureDraft.addShape(mShape);
    }

    @Override
    public void unExecute() {

    }
}
