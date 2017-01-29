package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.controller.DragType;
import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;

import javax.vecmath.Vector2f;

public class ResizeShapeCommand implements ICommand {
    private Vector2f mStartSize;
    private Vector2f mStartCenter;
    private Vector2f mEndSize = new Vector2f();
    private Vector2f mEndCenter = new Vector2f();
    private Shape mShape;

    public ResizeShapeCommand(Shape shape,
                              Vector2f startSize,
                              Vector2f startCenter,
                              Vector2f pos,
                              DragType dragType) {
        mShape = shape;
        mStartSize = startSize;
        mStartCenter = startCenter;
        ShapeDiagram diagram = shape.getDiagram();
        switch (dragType) {
            case LeftBottom:
                mEndSize.set(diagram.getRight() - pos.x, pos.y - diagram.getTop());
                mEndCenter.set(
                        startCenter.x + (mEndSize.x - startSize.x) / -2f,
                        startCenter.y + (mEndSize.y - startSize.y) / 2f);
                break;
            case LeftTop:
                mEndSize.set(diagram.getRight() - pos.x, diagram.getBottom() - pos.y);
                mEndCenter.set(
                        startCenter.x + (mEndSize.x - startSize.x) / -2f,
                        startCenter.y + (mEndSize.y - startSize.y) / -2f);

                break;
            case RightBottom:
                mEndSize.set(pos.x - diagram.getLeft(), pos.y - diagram.getTop());
                mEndCenter.set(
                        startCenter.x + (mEndSize.x - startSize.x) / 2f,
                        startCenter.y + (mEndSize.y - startSize.y) / 2f);
                break;
            case RightTop:
                mEndSize.set(pos.x - diagram.getLeft(), diagram.getBottom() - pos.y);
                mEndCenter.set(
                        startCenter.x + (mEndSize.x - startSize.x) / 2f,
                        startCenter.y + (mEndSize.y - startSize.y) / -2f);
                break;
            default:
                break;
        }
    }

    @Override
    public void execute() {
        mShape.setSize(mEndSize.x, mEndSize.y);
        mShape.setCenter(mEndCenter);
    }

    @Override
    public void unExecute() {
        mShape.setSize(mStartSize.x, mStartSize.y);
        mShape.setCenter(mStartCenter);
    }
}
