package com.example.alexander.shapespainter.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.alexander.shapespainter.controller.commands.AddShapeCommand;
import com.example.alexander.shapespainter.controller.commands.MoveShapeCommand;
import com.example.alexander.shapespainter.controller.commands.RemoveShapeCommand;
import com.example.alexander.shapespainter.controller.commands.ResizeShapeCommand;
import com.example.alexander.shapespainter.model.SelectShapeDiagram;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.ShapesList;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.Constant.DEFAULT_RADIUS_DRAG_POINT;

public class Controller {
    private ShapesList mShapesList;
    private Context mContext;
    private SelectShapeDiagram mSelectDiagramShape = new SelectShapeDiagram();
    private DragType mDragType = null;
    private MouseActionType mMouseActionType = MouseActionType.None;
    private CommandStack mCommandStack = new CommandStack();
    private ICommand mCommandResize;

    private Vector2f mStartPositionClickMouse = new Vector2f();
    private Vector2f mStartSizeShapeThenClickMouse = new Vector2f();
    private Vector2f mStartCenterShapeThenClickMouse = new Vector2f();
    private Vector2f mDistanceFromShapeCenterToMousePos = new Vector2f();

    public Controller(Context context, ShapesList shapesList) {
        mContext = context;
        mShapesList = shapesList;
    }


    public SelectShapeDiagram getSelectDiagramShape() {
        return mSelectDiagramShape;
    }

    public ShapesList getShapesDraft() {
        return mShapesList;
    }

    public void addEllipseOnCanvas() {
        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Ellipse));
    }

    public void addTriangleOnCanvas() {
        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Triangle));
    }

    public void addRectangleOnCanvas() {
        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Rectangle));
    }

    public void undoCommand() {//назад
        if (mCommandStack.undoEnabled()) {
            mCommandStack.undo();
        } else {
            setMessage("отменить нельзя");
        }
        mSelectDiagramShape.setShape(null);
    }

    public void redoCommand() {//вперед
        if (mCommandStack.redoEnabled()) {
            mCommandStack.redo();
        } else {
            setMessage("вернуть нельзя");
        }
        mSelectDiagramShape.setShape(null);
    }

    public void deleteSelectedShape() {
        if (mSelectDiagramShape.getShape() != null) {
            selectShapeClear();
        } else {
            setMessage("чтобы удалить фигуру, нужно ее сначала выделить");
        }
    }

    private void selectShapeClear() {
        mCommandStack.add(new RemoveShapeCommand(mShapesList, mSelectDiagramShape.getShape()));
        mSelectDiagramShape.setShape(null);
        mCommandResize = null;
    }

    public void updateShapes(Vector2f mousePos) {
        for (Shape shape : mShapesList.getShapes()) {
            switch (mMouseActionType) {
                case Down:
                    mouseDown(shape, mousePos);
                    updateResizeShape(mousePos);
                    break;
                case Move:
                    mouseMoved(shape, mousePos);
                    updateResizeShape(mousePos);
                    break;
                case Up:
                    mouseUp(mousePos);
                    break;
            }

        }
    }

    private void mouseDown(Shape shape, Vector2f mousePos) {
        if (PointInsideShapeManager.isPointInside(shape, mousePos) && !calculateDragType(mousePos)) {
            mCommandResize = null;
            mSelectDiagramShape.setShape(shape);
            mDragType = null;
            mStartPositionClickMouse = mousePos;
            mDistanceFromShapeCenterToMousePos.set(
                    Math.abs(mousePos.x - shape.getCenter().x),
                    Math.abs(mousePos.y - shape.getCenter().y));
        }
        if (mSelectDiagramShape.getShape() != null
                && !PointInsideShapeManager.isPointInside(mSelectDiagramShape.getShape(), mousePos)
                && mMouseActionType == MouseActionType.Down && !calculateDragType(mousePos)) {
            mDragType = null;
            mSelectDiagramShape.setShape(null);
        }
        if (mSelectDiagramShape.getShape() != null) {
            mStartCenterShapeThenClickMouse = mSelectDiagramShape.getShape().getCenter();
            mStartSizeShapeThenClickMouse = mSelectDiagramShape.getShape().getSize();
        }
    }

    private void mouseMoved(Shape shape, Vector2f mousePos) {
        if (shape == mSelectDiagramShape.getShape() && mDragType == null) {
            new MoveShapeCommand(
                    mSelectDiagramShape.getShape(),
                    mousePos,
                    mStartPositionClickMouse,
                    mDistanceFromShapeCenterToMousePos).execute();
        }
    }

    private void mouseUp(Vector2f mousePos) {
        if (mSelectDiagramShape.getShape() != null) {
            if (mCommandResize != null) {
                mCommandStack.add(mCommandResize);
            }
        }
        mDragType = null;
    }

    private void updateResizeShape(Vector2f mousePos) {
        Shape selectShape = mSelectDiagramShape.getShape();
        if (selectShape != null && mMouseActionType != MouseActionType.Move) {
            calculateDragType(mousePos);
        }
        if (mDragType != null) {
            mCommandResize = new ResizeShapeCommand(selectShape,
                    mStartSizeShapeThenClickMouse,
                    mStartCenterShapeThenClickMouse,
                    mousePos,
                    mDragType);
            mCommandResize.execute();
        }
        if (mDragType == null && mMouseActionType == MouseActionType.Up) {
            mCommandStack.add(new MoveShapeCommand(mSelectDiagramShape.getShape(), mousePos, mStartPositionClickMouse, mDistanceFromShapeCenterToMousePos));
        }
    }

    private void setMessage(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean calculateDragType(Vector2f mousePos) {
        int sizeInvisibleRadiusForUsability = 15;
        if (mSelectDiagramShape.getShape() != null) {
            ShapeDiagram shapeDiagram = mSelectDiagramShape.getShape().getDiagram();
            if (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2)
                    + Math.pow((mousePos.y - shapeDiagram.getTop()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2) <= 1) {
                mDragType = DragType.LeftTop;
                return true;
            }
            if (Math.pow((mousePos.x - shapeDiagram.getRight()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2)
                    + Math.pow((mousePos.y - shapeDiagram.getTop()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2) <= 1) {
                mDragType = DragType.RightTop;
                return true;
            }
            if (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2)
                    + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2) <= 1) {
                mDragType = DragType.LeftBottom;
                return true;
            }
            if (Math.pow((mousePos.x - shapeDiagram.getRight()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2)
                    + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2)
                    / Math.pow(DEFAULT_RADIUS_DRAG_POINT + sizeInvisibleRadiusForUsability, 2) <= 1) {
                mDragType = DragType.RightBottom;
                return true;
            }
        }
        return false;
    }

    public void setMouseMotionType(MouseActionType mouseActionType) {
        mMouseActionType = mouseActionType;
    }
}
