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
    private Shape mShape;

    public SelectShapeDiagram(Shape shape) {
        mShape = shape;
    }

    public void setShape(Shape shape) {
        mShape = shape;
    }

    public Shape getShape() {
        return mShape;
    }

    public ShapeDiagram getShapeDiagram() {
        ShapeDiagram shapeDiagram = null;
        Vector<Vector2f> dataShape = mShape.getDataShape();
        switch (mShape.getType()) {
            case Ellipse:
                shapeDiagram = new ShapeDiagram(
                        dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y,
                        dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x - dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x,
                        dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).x + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).x,
                        dataShape.get(DATA_SHAPE_CENTER_ELLIPSE_INDEX).y + dataShape.get(DATA_SHAPE_SIZE_ELLIPSE_INDEX).y);

                break;
            case Triangle:
                shapeDiagram = new ShapeDiagram(
                        dataShape.get(DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX).y,
                        dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x,
                        dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).x,
                        dataShape.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX).y);
                break;
            case Rectangle:
                shapeDiagram = new ShapeDiagram(
                        dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).y,
                        dataShape.get(DATA_SHAPE_LEFT_TOP_RECTANGLE_INDEX).x,
                        dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).x,
                        dataShape.get(DATA_SHAPE_RIGHT_BOTTOM_RECTANGLE_INDEX).y);
                break;
        }
        return shapeDiagram;
    }

}
