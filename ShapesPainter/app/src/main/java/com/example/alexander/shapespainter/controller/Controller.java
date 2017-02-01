package com.example.alexander.shapespainter.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
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
import com.example.alexander.shapespainter.model.Tools;

import java.util.Map;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_RADIUS_DRAG_POINT;
import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_SHIFT_POSITION_X_FOR_REDO_TOOLBAR;
import static com.example.alexander.shapespainter.constants.ConstWorld.DEFAULT_SHIFT_POSITION_X_FOR_UNDO_TOOLBAR;

public class Controller {
    private ShapesList mShapesList;
    private Tools mTools;
    private Context mContext;
    private int mScreenWidth;
    private SelectShapeDiagram mSelectDiagramShape = new SelectShapeDiagram();
    private DragType mDragType = DragType.None;
    private MouseActionType mMouseActionType = MouseActionType.None;
    private CommandStack mCommandStack = new CommandStack();
    private Vector2f mStartPositionClickMouse = new Vector2f();
    private Vector2f mStartSizeShapeThenClickMouse = new Vector2f();
    private Vector2f mStartCenterShapeThenClickMouse = new Vector2f();
    private ICommand mCommandResize;

    private Vector2f mDistanceFromShapeCenterToMousePos = new Vector2f();

    public Controller(Context context, int screenWidth) {
        mTools = new Tools(context, screenWidth);
        mScreenWidth = screenWidth;
        mContext = context;
        mShapesList = new ShapesList();
    }

    public Controller(Context context, int screenWidth, ShapesList shapesList) {
        mTools = new Tools(context, screenWidth);
        mScreenWidth = screenWidth;
        mContext = context;
        mShapesList = shapesList;
    }


    public SelectShapeDiagram getSelectDiagramShape() {
        return mSelectDiagramShape;
    }

    public ShapesList getShapesDraft() {
        return mShapesList;
    }

    public ShapesList getToolsDraft() {
        return mTools.getToolsList();
    }

    public Map<Bitmap, Vector2f> getBitmapTools() {
        return mTools.getBitmapToolsList();
    }


    public void updateToolbars(Vector2f mousePos) {
        updateShapesTools(mousePos);
        updateBitmaps(mousePos);
    }

    private void updateShapesTools(Vector2f mousePos) {
        for (Shape shape : mTools.getToolsList().getShapes()) {
            if (PointInsideShapeManager.isPointInside(shape, mousePos)) {
                switch (shape.getType()) {
                    case Ellipse:
                        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Ellipse));
                        break;
                    case Triangle:
                        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Triangle));
                        break;
                    case Rectangle:
                        mCommandStack.add(new AddShapeCommand(mShapesList, ShapeType.Rectangle));
                        break;
                }
            }
        }
    }

    private void undoCommand() {//назад
        if (mCommandStack.undoEnabled()) {
            mCommandStack.undo();
        } else {
            setMessage("отменить нельзя");
        }
        mSelectDiagramShape.setShape(null);
    }

    private void redoCommand() {//вперед
        if (mCommandStack.redoEnabled()) {
            mCommandStack.redo();
        } else {
            setMessage("вернуть нельзя");
        }
        mSelectDiagramShape.setShape(null);
    }

    private void selectShapeClear() {
        mCommandStack.add(new RemoveShapeCommand(mShapesList, mSelectDiagramShape.getShape()));
        mSelectDiagramShape.setShape(null);
        mCommandResize = null;
    }

    private void updateBitmaps(Vector2f mousePos) {
        Bitmap bitmap;
        Vector2f bitmapPos;
        for (Map.Entry entry : mTools.getBitmapToolsList().entrySet()) {
            bitmap = (Bitmap) entry.getKey();
            bitmapPos = (Vector2f) entry.getValue();
            RectF bitmapRect = new RectF(
                    bitmapPos.x,
                    bitmapPos.y,
                    bitmapPos.x + bitmap.getWidth(),
                    bitmapPos.y + bitmap.getHeight());
            if (bitmapPos.x == mScreenWidth - DEFAULT_SHIFT_POSITION_X_FOR_UNDO_TOOLBAR) {
                //bitmap = undo
                if (PointInsideShapeManager.isPointInside(bitmapRect, mousePos, bitmap)) {
                    undoCommand();
                }
            } else if (bitmapPos.x == mScreenWidth - DEFAULT_SHIFT_POSITION_X_FOR_REDO_TOOLBAR) {
                //bitmap = redo
                if (PointInsideShapeManager.isPointInside(bitmapRect, mousePos, bitmap)) {
                    redoCommand();
                }
            } else {
                //bitmap = trash
                boolean isInside = PointInsideShapeManager.isPointInside(bitmapRect, mousePos, bitmap);
                if (isInside && mSelectDiagramShape.getShape() != null) {
                    selectShapeClear();
                } else if (isInside) {
                    setMessage("чтобы удалить фигуру, нужно ее сначала выделить");
                }
            }
        }
    }


    public void updateShapes(Vector2f mousePos) {
        for (Shape shape : mShapesList.getShapes()) {
            switch (mMouseActionType) {
                case Down:
                    mouseDown(shape, mousePos);
                    break;
                case Move:
                    mouseMoved(shape,mousePos);
                    break;
                case Up:
                    mouseUp(mousePos);
                    break;
            }
            updateResizeShape(mousePos);
        }
    }

    private void mouseDown(Shape shape, Vector2f mousePos) {
        if (PointInsideShapeManager.isPointInside(shape, mousePos)) {
            mCommandResize = null;
            mDragType = DragType.None;
            mSelectDiagramShape.setShape(shape);
            mStartPositionClickMouse = mousePos;
            mDistanceFromShapeCenterToMousePos.set(
                    Math.abs(mousePos.x - shape.getCenter().x),
                    Math.abs(mousePos.y - shape.getCenter().y));
        }
        if (mSelectDiagramShape.getShape() != null
                && !PointInsideShapeManager.isPointInside(mSelectDiagramShape.getShape(), mousePos)
                && mMouseActionType == MouseActionType.Down && !calculateDragType(mousePos)) {
            mDragType = DragType.None;
            mSelectDiagramShape.setShape(null);
        }
        if (mSelectDiagramShape.getShape() != null) {
            mStartCenterShapeThenClickMouse = mSelectDiagramShape.getShape().getCenter();
            mStartSizeShapeThenClickMouse = mSelectDiagramShape.getShape().getSize();
        }
    }

    private void mouseMoved(Shape shape, Vector2f mousePos) {
        if (shape == mSelectDiagramShape.getShape() && mDragType == DragType.None) {
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
            if (mDragType == DragType.None) {
                mCommandStack.add(new MoveShapeCommand(mSelectDiagramShape.getShape(), mousePos, mStartPositionClickMouse, mDistanceFromShapeCenterToMousePos));
            }
        }
        mDragType = DragType.None;
    }

    private void updateResizeShape(Vector2f mousePos) {
        Shape selectShape = mSelectDiagramShape.getShape();
        if (selectShape != null) {
            calculateDragType(mousePos);
        }
        if (mDragType != DragType.None) {
            mCommandResize = new ResizeShapeCommand(selectShape,
                    mStartSizeShapeThenClickMouse,
                    mStartCenterShapeThenClickMouse,
                    mousePos,
                    mDragType);
            mCommandResize.execute();
        }
    }

    private void setMessage(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean calculateDragType(Vector2f mousePos) {
        ShapeDiagram shapeDiagram = mSelectDiagramShape.getShape().getDiagram();
        if (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mousePos.y - shapeDiagram.getTop()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2) <= 1) {
            mDragType = DragType.LeftTop;
            return true;
        }
        if (Math.pow((mousePos.x - shapeDiagram.getRight()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mousePos.y - shapeDiagram.getTop()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2) <= 1) {
            mDragType = DragType.RightTop;
            return true;
        }
        if (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2) <= 1) {
            mDragType = DragType.LeftBottom;
            return true;
        }
        if (Math.pow((mousePos.x - shapeDiagram.getRight()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2) <= 1) {
            mDragType = DragType.RightBottom;
            return true;
        }
        return false;
    }

    public void setMouseMotionType(MouseActionType mouseActionType) {
        mMouseActionType = mouseActionType;
    }
}
