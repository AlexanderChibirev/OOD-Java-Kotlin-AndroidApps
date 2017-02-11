package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.controller.DragType;
import com.example.alexander.shapespainter.controller.ICommand;
import com.example.alexander.shapespainter.model.IShape;
import com.example.alexander.shapespainter.model.SelectShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeDiagram;

import javax.vecmath.Vector2f;

public class ResizeShapeCommand implements ICommand {
    private Vector2f mStartSize;
    private Vector2f mStartCenter;
    private Vector2f mEndSize = new Vector2f();
    private Vector2f mEndCenter = new Vector2f();
    private IShape mShape;
    private SelectShapeDiagram mSelectShapeDiagram;

    public ResizeShapeCommand(SelectShapeDiagram selectShapeDiagram,
                              Vector2f startSize,
                              Vector2f startCenter,
                              Vector2f pos,
                              DragType dragType) {
        mShape = selectShapeDiagram.getShape();
        mStartSize = startSize;
        mStartCenter = startCenter;
        mSelectShapeDiagram = selectShapeDiagram;
        checkAnglesShape(pos, dragType);
    }

    private void checkAnglesShape(Vector2f pos, DragType dragType) {
        ShapeDiagram diagram = mSelectShapeDiagram.getShapeDiagram();
        switch (dragType) {
            case LeftBottom:
                calculateDataForLeftBottomAngle(pos, diagram);
                break;
            case LeftTop:
                calculateDataForLeftTopAngle(pos, diagram);
                break;
            case RightBottom:
                calculateDataForRightBottomAngle(pos, diagram);
                break;
            case RightTop:
                calculateDataForRightTopAngle(pos, diagram);
                break;
            default:
                break;
        }
    }

    private void calculateDataForRightTopAngle(Vector2f pos, ShapeDiagram diagram) {
        mEndSize.set(pos.x - diagram.getLeft(), diagram.getBottom() - pos.y);
        mEndCenter.set(
                mStartCenter.x + (mEndSize.x - mStartSize.x) / 2f,
                mStartCenter.y + (mEndSize.y - mStartSize.y) / -2f);
    }

    private void calculateDataForRightBottomAngle(Vector2f pos, ShapeDiagram diagram) {
        mEndSize.set(pos.x - diagram.getLeft(), pos.y - diagram.getTop());
        mEndCenter.set(
                mStartCenter.x + (mEndSize.x - mStartSize.x) / 2f,
                mStartCenter.y + (mEndSize.y - mStartSize.y) / 2f);
    }

    private void calculateDataForLeftTopAngle(Vector2f pos, ShapeDiagram diagram) {
        mEndSize.set(diagram.getRight() - pos.x, diagram.getBottom() - pos.y);
        mEndCenter.set(
                mStartCenter.x + (mEndSize.x - mStartSize.x) / -2f,
                mStartCenter.y + (mEndSize.y - mStartSize.y) / -2f);
    }

    private void calculateDataForLeftBottomAngle(Vector2f pos, ShapeDiagram diagram) {
        mEndSize.set(diagram.getRight() - pos.x, pos.y - diagram.getTop());
        mEndCenter.set(
                mStartCenter.x + (mEndSize.x - mStartSize.x) / -2f,
                mStartCenter.y + (mEndSize.y - mStartSize.y) / 2f);
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
