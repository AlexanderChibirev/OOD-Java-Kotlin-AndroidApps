package com.example.alexander.shapespaintermvp.mvp.presenters;

import android.content.Context;
import android.graphics.Canvas;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.shapespaintermvp.mvp.common.FileSystemUtils;
import com.example.alexander.shapespaintermvp.mvp.common.PointInsideShapeManager;
import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;
import com.example.alexander.shapespaintermvp.mvp.views.CanvasView;
import com.example.alexander.shapespaintermvp.ui.views.ShapeDiagram;

import java.util.ArrayList;
import java.util.Vector;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_CENTER_ELLIPSE_INDEX;
import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX;
import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX;
import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX;
import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_SIZE_ELLIPSE_INDEX;
import static com.example.alexander.shapespaintermvp.constants.Constant.DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX;


@InjectViewState
public class CanvasPresenter extends MvpPresenter<CanvasView> {
    private IShape mSelectedShape;

    public void painterShapes(Canvas canvas, ShapesList shapesList) {
        getViewState().painterShapes(canvas, shapesList);
    }

    public void down(Vector2f mousePos, ArrayList<IShape> shapesList) {
        for (int i = shapesList.size() - 1; i > -1; i--) {
            IShape shape = shapesList.get(i);
            if (PointInsideShapeManager.isPointInside(shape, mousePos)) {
                mSelectedShape = shape;
                break;
            } else {
                mSelectedShape = null;
            }
        }
        painterShapeDiagram();
    }

    private void painterShapeDiagram() {
        if (mSelectedShape != null) {
            getViewState().painterShapeDiagram(getShapeDiagram(mSelectedShape));
        } else {
            getViewState().painterShapeDiagram(null);
        }
    }

    public void move(Vector2f mousePos, ArrayList<IShape> shapesList) {
    }

    public void up(Vector2f mousePos, ArrayList<IShape> shapesList) {

    }

    public void saveStateShape(ShapesList shapesList, Context context) {
        FileSystemUtils.saveFileWithStateShapes(shapesList, context);
    }

    private ShapeDiagram getShapeDiagram(IShape shape) {
        Vector<Vector2f> dataShape = shape.getDataShape();
        ShapeType shapeType = shape.getType();
        ShapeDiagram shapeDiagram = new ShapeDiagram(0, 0, 0, 0);
        switch (shapeType) {
            case Ellipse:
                shapeDiagram.setTop(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y
                        - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y);
                shapeDiagram.setLeft(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x
                        - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x);
                shapeDiagram.setRight(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x
                        + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x);
                shapeDiagram.setBottom(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y
                        + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y);
                break;
            case Triangle:
                shapeDiagram.setTop(dataShape.get(DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX).y);
                shapeDiagram.setLeft(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x);
                shapeDiagram.setRight(dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).x);
                shapeDiagram.setBottom(dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).y);
                break;
            case Rectangle:
                shapeDiagram.setTop(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).y);
                shapeDiagram.setLeft(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x);
                shapeDiagram.setRight(dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).x);
                shapeDiagram.setBottom(dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).y);
                break;
        }
        return shapeDiagram;
    }
}
