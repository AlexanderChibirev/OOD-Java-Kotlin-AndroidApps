package com.example.alexander.shapespainter.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.widget.Toast;

import com.example.alexander.shapespainter.controller.commands.AddShapeCommand;
import com.example.alexander.shapespainter.controller.commands.RemoveShapeCommand;
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
    private ShapesList mShapesList = new ShapesList();
    private Tools mTools;
    private Vector2f mMousePos = new Vector2f(0, 0);
    private Context mContext;
    private int mScreenWidth;
    private SelectShapeDiagram mSelectDiagramShape = new SelectShapeDiagram();
    private DragType mDragType = DragType.None;
    private MouseActionType mMouseActionType = MouseActionType.None;
    private CommandStack mCommandStack = new CommandStack();

    public Controller(Context context, int screenWidth) {
        mTools = new Tools(context, screenWidth);
        mScreenWidth = screenWidth;
        mContext = context;

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


    public void updateToolbars(Vector2f pos) {
        mMousePos = pos;
        updateShapesTools();
        updateBitmaps();
    }

    private void updateShapesTools() {
        for (Shape shape : mTools.getToolsList().getShapes()) {
            if (PointInsideShapeManager.isPointInside(shape, mMousePos)) {
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
    }

    private void updateBitmaps() {
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
                if (PointInsideShapeManager.isPointInside(bitmapRect, mMousePos, bitmap)) {
                    undoCommand();
                }
            } else if (bitmapPos.x == mScreenWidth - DEFAULT_SHIFT_POSITION_X_FOR_REDO_TOOLBAR) {
                //bitmap = redo
                if (PointInsideShapeManager.isPointInside(bitmapRect, mMousePos, bitmap)) {
                    redoCommand();
                }
            } else {
                //bitmap = trash
                boolean isInside = PointInsideShapeManager.isPointInside(bitmapRect, mMousePos, bitmap);
                if (isInside && mSelectDiagramShape.getShape() != null) {
                    selectShapeClear();
                } else if (isInside) {
                    setMessage("чтобы удалить фигуру, нужно ее сначала выделить");
                }
            }
        }
    }

    public void updateShapes(Vector2f pos) {
        for (Shape shape : mShapesList.getShapes()) {
            switch (mMouseActionType) {
                case Down:
                    if (PointInsideShapeManager.isPointInside(shape, pos)) {
                        mSelectDiagramShape.setShape(shape);
                    }
                    if (mSelectDiagramShape.getShape() != null
                            && !PointInsideShapeManager.isPointInside(mSelectDiagramShape.getShape(), pos)
                            && mMouseActionType == MouseActionType.Down) {
                        mSelectDiagramShape.setShape(null);
                    }
                    break;
                case Move:
                    if (shape == mSelectDiagramShape.getShape()) {
                        shape.setCenter(pos);
                    }
                    break;
                case Up:
                    //mCommandStack.add(new MoveShapeCommand(mSelectDiagramShape.getShape(), pos));
                    break;
            }

            //updateResizeShape();
        }
    }

    private void updateResizeShape() {
        if (mSelectDiagramShape.getShape() != null) {//TODO::блок для выполнения ресайза
            getDragType();
            switch (mDragType) {
                case LeftBottom:
                    break;
                case LeftTop:
                    break;
                case RightBottom:

                    break;
                case RightTop:

                    break;
                default:
                    break;
            }
        }
    }

    private void setMessage(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void getDragType() {
        ShapeDiagram shapeDiagram = mSelectDiagramShape.getShape().getDiagram();
        double leftValue = Math.pow((mMousePos.x - shapeDiagram.getLeft()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mMousePos.y - shapeDiagram.getTop()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2);
        if (leftValue <= 1) {
            mDragType = DragType.LeftTop;
        }

        leftValue = Math.pow((mMousePos.x - shapeDiagram.getRight()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mMousePos.y - shapeDiagram.getTop()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2);
        if (leftValue <= 1) {
            mDragType = DragType.RightTop;
        }

        leftValue = Math.pow((mMousePos.x - shapeDiagram.getLeft()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mMousePos.y - shapeDiagram.getBottom()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2);
        if (leftValue <= 1) {
            mDragType = DragType.LeftBottom;
        }

        leftValue = Math.pow((mMousePos.x - shapeDiagram.getRight()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2)
                + Math.pow((mMousePos.y - shapeDiagram.getBottom()), 2) / Math.pow(DEFAULT_RADIUS_DRAG_POINT, 2);
        if (leftValue <= 1) {
            mDragType = DragType.RightBottom;
        }

    }

    private void resizeShape() {
        ShapeDiagram frame = mSelectDiagramShape.getShape().getDiagram();
        Vector2f newSize = new Vector2f();
        Vector2f newCenter = new Vector2f();
    }

    public void setMouseMotionType(MouseActionType mouseActionType) {
        mMouseActionType = mouseActionType;
    }
}
