package com.example.alexander.shapespaintermvp.mvp.common;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import javax.vecmath.Vector2f;

public class PainterUtils {
    public static void drawPolygon(Canvas canvas, int color, Vector2f[] points) {
        // line at minimum...
        if (points.length < 2) {
            return;
        }
        // paint
        Paint polyPaint = new Paint();
        polyPaint.setColor(color);
        polyPaint.setStyle(Paint.Style.FILL);
        // path
        Path polyPath = new Path();
        polyPath.moveTo(points[0].x, points[0].y);
        int i, len;
        len = points.length;
        for (i = 0; i < len; i++) {
            polyPath.lineTo(points[i].x, points[i].y);
        }
        polyPath.lineTo(points[0].x, points[0].y);
        canvas.drawPath(polyPath, polyPaint);
    }
}
