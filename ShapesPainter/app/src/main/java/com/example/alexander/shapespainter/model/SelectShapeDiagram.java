package com.example.alexander.shapespainter.model;

import java.util.Vector;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_CENTER_ELLIPSE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_SIZE_ELLIPSE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX;

public class SelectShapeDiagram {
    private IShape mSelectedShape;
    private ShapeDiagram mShapeDiagram = new ShapeDiagram(0, 0, 0, 0);

    public SelectShapeDiagram(IShape shape) {
        mSelectedShape = shape;
    }

    public void setSelectedShape(IShape shape) {
        mSelectedShape = shape;
    }

    public IShape getSelectedShape() {
        return mSelectedShape;
    }

    public ShapeDiagram getShapeDiagram() {
        Vector<Vector2f> dataShape = mSelectedShape.getDataShape();
        if (mSelectedShape != null) {
            ShapeType shapeType = mSelectedShape.getType();
            switch (shapeType) {
                case Ellipse:
                    mShapeDiagram.setTop(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y
                            - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y);
                    mShapeDiagram.setLeft(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x
                            - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x);
                    mShapeDiagram.setRight(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x
                            + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x);
                    mShapeDiagram.setBottom(dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y
                            + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y);
                    break;
                case Triangle:
                    mShapeDiagram.setTop(dataShape.get(DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX).y);
                    mShapeDiagram.setLeft(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x);
                    mShapeDiagram.setRight(dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).x);
                    mShapeDiagram.setBottom(dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).y);
                    break;
                case Rectangle:
                    mShapeDiagram.setTop(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).y);
                    mShapeDiagram.setLeft(dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x);
                    mShapeDiagram.setRight(dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).x);
                    mShapeDiagram.setBottom(dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).y);
                    break;
            }
        }
        return mShapeDiagram;
    }
}
