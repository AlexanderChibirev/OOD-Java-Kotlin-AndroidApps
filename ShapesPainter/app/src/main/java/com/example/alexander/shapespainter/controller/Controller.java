package com.example.alexander.shapespainter.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.alexander.shapespainter.controller.commands.AddShapeCommand;
import com.example.alexander.shapespainter.controller.commands.MoveShapeCommand;
import com.example.alexander.shapespainter.controller.commands.RemoveShapeCommand;
import com.example.alexander.shapespainter.controller.commands.ResizeShapeCommand;
import com.example.alexander.shapespainter.model.IShape;
import com.example.alexander.shapespainter.model.SelectShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.ShapesList;
import com.example.alexander.shapespainter.utils.FileSystem;

import java.io.IOException;

import javax.vecmath.Vector2f;

public class Controller {
    private ShapesList mShapesList = new ShapesList();
    private Context mContext;
    private SelectShapeDiagram mSelectDiagramShape = new SelectShapeDiagram(null);
    private DragType mDragType = null;
    private CommandStack mCommandStack = new CommandStack();
    private ShapeFactory mShapeFactory = new ShapeFactory();

    private Vector2f mStartPositionClickMouse = new Vector2f();
    private Vector2f mStartSizeShapeThenClickMouse = new Vector2f();
    private Vector2f mStartCenterShapeThenClickMouse = new Vector2f();
    private Vector2f mDistanceFromShapeCenterToMousePos = new Vector2f();

    public Controller(Context context) {
        mContext = context;
    }

    public void readFileWithStateShape() {
        try {
            FileSystem.readFileWithStateShapes(mShapesList, mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShapesList getShapesList() {
        return mShapesList;
    }

    public SelectShapeDiagram getSelectDiagramShape() {
        return mSelectDiagramShape;
    }

    public ShapesList getShapesDraft() {
        return mShapesList;
    }

    public void addShape(ShapeType shapeType) {
        AddShapeCommand addShapeCommand = new AddShapeCommand(mShapesList, shapeType, mShapeFactory);
        addShapeCommand.execute();
        mCommandStack.add(addShapeCommand);
        mSelectDiagramShape.setSelectedShape(mShapesList.getShapes().get(mShapesList.getShapes().size() - 1));
    }

    public void undoCommand() {//назад
        if (mCommandStack.undoEnabled()) {
            mCommandStack.undo();
            mSelectDiagramShape.setSelectedShape(null);
        } else {
            setMessage("отменить нельзя");
        }
    }

    public void redoCommand() {//вперед
        if (mCommandStack.redoEnabled()) {
            mCommandStack.redo();
            mSelectDiagramShape.setSelectedShape(null);
        } else {
            setMessage("вернуть нельзя");
        }
    }

    public void deleteSelectedShape() {
        if (mSelectDiagramShape.getSelectedShape() != null) {
            selectShapeClear();
        } else {
            setMessage("чтобы удалить фигуру, нужно ее сначала выделить");
        }
    }

    private void selectShapeClear() {
        RemoveShapeCommand removeShapeCommand = new RemoveShapeCommand(mShapesList, mSelectDiagramShape.getSelectedShape());
        removeShapeCommand.execute();
        mCommandStack.add(removeShapeCommand);
        mSelectDiagramShape.setSelectedShape(null);
    }

    public void mouseDown(IShape shape, Vector2f mousePos) {
        if (PointInsideShapeManager.isPointInside(shape, mousePos) && !calculateDragType(mousePos)) {
            mSelectDiagramShape.setSelectedShape(shape);
            mDragType = null;
            mStartPositionClickMouse = mousePos;
            mDistanceFromShapeCenterToMousePos.set(
                    Math.abs(mousePos.x - shape.getCenter().x),
                    Math.abs(mousePos.y - shape.getCenter().y));
        }
        IShape selectedShape = mSelectDiagramShape.getSelectedShape();
        if (selectedShape != null
                && !PointInsideShapeManager.isPointInside(selectedShape, mousePos)
                && !calculateDragType(mousePos)) {
            mDragType = null;
            mSelectDiagramShape.setSelectedShape(null);
        }
        if (selectedShape != null) {
            mStartCenterShapeThenClickMouse = selectedShape.getCenter();
            mStartSizeShapeThenClickMouse = selectedShape.getSize();
        }
        calculateDragType(mousePos);
    }

    public void mouseMoved(IShape shape, Vector2f mousePos) {
        moveShapeCommand(mousePos, shape);
        updateResizeShape(mousePos);
    }

    private MoveShapeCommand moveShapeCommand(Vector2f mousePos, IShape shape) {
        MoveShapeCommand moveShapeCommand = null;
        IShape selectedShape = mSelectDiagramShape.getSelectedShape();
        boolean isMovedShape = mousePos.x != mStartPositionClickMouse.x || mousePos.y != mStartPositionClickMouse.y;
        if (shape == selectedShape
                && selectedShape != null
                && mDragType == null
                && isMovedShape) {
            moveShapeCommand = new MoveShapeCommand(
                    selectedShape,
                    mousePos,
                    mStartPositionClickMouse,
                    mDistanceFromShapeCenterToMousePos);
            moveShapeCommand.execute();
        }
        return moveShapeCommand;
    }

    public void mouseUp(IShape shape, Vector2f mousePos) {
        IShape selectedShape = mSelectDiagramShape.getSelectedShape();
        if (selectedShape == shape && shape != null) {
            ResizeShapeCommand resizeShapeCommand = resizeShapeCommand(mousePos);
            if (resizeShapeCommand != null) {
                mCommandStack.add(resizeShapeCommand);
            }
        }
        MoveShapeCommand moveShapeCommand = moveShapeCommand(mousePos, selectedShape);
        if (moveShapeCommand != null && selectedShape == shape) {
            mCommandStack.add(moveShapeCommand);
        }
    }

    private ResizeShapeCommand resizeShapeCommand(Vector2f mousePos) {
        ResizeShapeCommand resizeCommand = null;
        if (mDragType != null && mStartCenterShapeThenClickMouse != mousePos) {
            resizeCommand = new ResizeShapeCommand(mSelectDiagramShape,
                    mStartSizeShapeThenClickMouse,
                    mStartCenterShapeThenClickMouse,
                    mousePos,
                    mDragType);
            resizeCommand.execute();
        }
        return resizeCommand;
    }

    private void updateResizeShape(Vector2f mousePos) {
        resizeShapeCommand(mousePos);
    }

    private void setMessage(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean calculateDragType(Vector2f mousePos) {
        if (mSelectDiagramShape.getSelectedShape() != null) {
            ShapeDiagram shapeDiagram = mSelectDiagramShape.getShapeDiagram();
            if (PointInsideShapeManager.isPointInsideLeftTopDragPoint(shapeDiagram, mousePos)) {
                mDragType = DragType.LeftTop;
                return true;
            }
            if (PointInsideShapeManager.isPointInsideRightTopDragPoint(shapeDiagram, mousePos)) {
                mDragType = DragType.RightTop;
                return true;
            }
            if (PointInsideShapeManager.isPointInsideLeftBottomDragPoint(shapeDiagram, mousePos)) {
                mDragType = DragType.LeftBottom;
                return true;
            }
            if (PointInsideShapeManager.isPointInsideRightBottomDragPoint(shapeDiagram, mousePos)) {
                mDragType = DragType.RightBottom;
                return true;
            }
        }
        return false;
    }

    public void saveStateShape() {
        FileSystem.saveFileWithStateShapes(getShapesDraft(), mContext);
    }
}
