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
import com.example.alexander.shapespainter.utils.FileSystem;

import java.io.IOException;

import javax.vecmath.Vector2f;

public class Controller {
    private ShapesList mShapesList = new ShapesList();
    private Context mContext;
    private SelectShapeDiagram mSelectDiagramShape = new SelectShapeDiagram(null);
    private DragType mDragType = null;
    private CommandStack mCommandStack = new CommandStack();

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

    public void addEllipse() {
        AddShapeCommand addShapeCommand = new AddShapeCommand(mShapesList, ShapeType.Ellipse);
        addShapeCommand.execute();
        mCommandStack.add(addShapeCommand);
        mSelectDiagramShape.setShape(mShapesList.getShapes().get(mShapesList.getShapes().size() - 1));
    }

    public void addTriangle() {
        AddShapeCommand addShapeCommand = new AddShapeCommand(mShapesList, ShapeType.Triangle);
        addShapeCommand.execute();
        mCommandStack.add(addShapeCommand);
        mSelectDiagramShape.setShape(mShapesList.getShapes().get(mShapesList.getShapes().size() - 1));
    }

    public void addRectangle() {
        AddShapeCommand addShapeCommand = new AddShapeCommand(mShapesList, ShapeType.Rectangle);
        addShapeCommand.execute();
        mCommandStack.add(addShapeCommand);
        mSelectDiagramShape.setShape(mShapesList.getShapes().get(mShapesList.getShapes().size() - 1));
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
        RemoveShapeCommand removeShapeCommand = new RemoveShapeCommand(mShapesList, mSelectDiagramShape.getShape());
        removeShapeCommand.execute();
        mCommandStack.add(removeShapeCommand);
        mSelectDiagramShape.setShape(null);
    }

    public void mouseDown(Shape shape, Vector2f mousePos) {
        if (PointInsideShapeManager.isPointInside(shape, mousePos) && !calculateDragType(mousePos)) {
            mSelectDiagramShape.setShape(shape);
            mDragType = null;
            mStartPositionClickMouse = mousePos;
            mDistanceFromShapeCenterToMousePos.set(
                    Math.abs(mousePos.x - shape.getCenter().x),
                    Math.abs(mousePos.y - shape.getCenter().y));
        }
        if (mSelectDiagramShape.getShape() != null
                && !PointInsideShapeManager.isPointInside(mSelectDiagramShape.getShape(), mousePos)
                && !calculateDragType(mousePos)) {
            mDragType = null;
            mSelectDiagramShape.setShape(null);
        }
        if (mSelectDiagramShape.getShape() != null) {
            mStartCenterShapeThenClickMouse = mSelectDiagramShape.getShape().getCenter();
            mStartSizeShapeThenClickMouse = mSelectDiagramShape.getShape().getSize();
        }
        calculateDragType(mousePos);
    }

    public void mouseMoved(Shape shape, Vector2f mousePos) {
        moveShapeCommand(mousePos, shape);
        updateResizeShape(mousePos);
    }

    private MoveShapeCommand moveShapeCommand(Vector2f mousePos, Shape shape) {
        MoveShapeCommand moveShapeCommand = null;
        if (shape == mSelectDiagramShape.getShape()
                && mDragType == null
                && mSelectDiagramShape.getShape() != null
                && mousePos.x != mStartPositionClickMouse.x
                && mousePos.y != mStartPositionClickMouse.y) {
            moveShapeCommand = new MoveShapeCommand(
                    mSelectDiagramShape.getShape(),
                    mousePos,
                    mStartPositionClickMouse,
                    mDistanceFromShapeCenterToMousePos);
            moveShapeCommand.execute();
        }
        return moveShapeCommand;
    }

    public void mouseUp(Shape shape, Vector2f mousePos) {
        if (mSelectDiagramShape.getShape() != null && mSelectDiagramShape.getShape() == shape) {
            ResizeShapeCommand resizeShapeCommand = resizeShapeCommand(mousePos);
            if (resizeShapeCommand != null) {
                mCommandStack.add(resizeShapeCommand);
            }
        }
        MoveShapeCommand moveShapeCommand = moveShapeCommand(mousePos, mSelectDiagramShape.getShape());
        if (moveShapeCommand != null && mSelectDiagramShape.getShape() == shape) {
            mCommandStack.add(moveShapeCommand);
        }
    }

    private ResizeShapeCommand resizeShapeCommand(Vector2f mousePos) {
        ResizeShapeCommand resizeCommand = null;
        if (mDragType != null
                && mStartCenterShapeThenClickMouse != mousePos) {
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
        if (mSelectDiagramShape.getShape() != null) {
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
