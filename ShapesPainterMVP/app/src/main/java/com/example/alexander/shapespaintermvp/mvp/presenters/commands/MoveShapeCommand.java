package com.example.alexander.shapespaintermvp.mvp.presenters.commands;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;

import javax.vecmath.Vector2f;

public class MoveShapeCommand implements ICommand {
    private IShape mShape;
    private Vector2f mToPoint;
    private Vector2f mFromPoint;
    private Vector2f mDistanceFromShapeCenterToMousePos;

    public MoveShapeCommand(IShape shape, Vector2f toPoint, Vector2f fromPoint, Vector2f distanceFromShapeCenterToMousePos) {
        mShape = shape;
        mFromPoint = fromPoint;
        mToPoint = toPoint;
        mDistanceFromShapeCenterToMousePos = distanceFromShapeCenterToMousePos;
    }

    @Override
    public void execute() {
        calculateDirectionMove();
    }

    private void calculateDirectionMove() {
        if (mToPoint.x > mShape.getCenter().x && mToPoint.y > mShape.getCenter().y) {
            mShape.setCenter(new Vector2f(
                    mToPoint.x - mDistanceFromShapeCenterToMousePos.x,
                    mToPoint.y - mDistanceFromShapeCenterToMousePos.y));
        } else if (mToPoint.x < mShape.getCenter().x && mToPoint.y < mShape.getCenter().y) {
            mShape.setCenter(new Vector2f(
                    mToPoint.x + mDistanceFromShapeCenterToMousePos.x,
                    mToPoint.y + mDistanceFromShapeCenterToMousePos.y));
        } else if (mToPoint.x < mShape.getCenter().x && mToPoint.y > mShape.getCenter().y) {
            mShape.setCenter(new Vector2f(
                    mToPoint.x + mDistanceFromShapeCenterToMousePos.x,
                    mToPoint.y - mDistanceFromShapeCenterToMousePos.y));
        } else if (mToPoint.x > mShape.getCenter().x && mToPoint.y < mShape.getCenter().y) {
            mShape.setCenter(new Vector2f(
                    mToPoint.x - mDistanceFromShapeCenterToMousePos.x,
                    mToPoint.y + mDistanceFromShapeCenterToMousePos.y));
        }
    }

    @Override
    public void unExecute() {
        mShape.setCenter(mFromPoint);
    }
}
