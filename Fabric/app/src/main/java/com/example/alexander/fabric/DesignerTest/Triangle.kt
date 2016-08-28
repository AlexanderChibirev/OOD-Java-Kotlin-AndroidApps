package com.example.alexander.fabric.DesignerTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.WorkWithShapes.ICanvas
import com.example.alexander.fabric.WorkWithShapes.Shape

/**
 * Created by Alexander on 25.06.2016.
 */
class Triangle(val vertex1: CordPoint, val vertex2: CordPoint, val vertex3: CordPoint, color: ColorEnum): Shape(color)
{
    override fun Draw(canvas: ICanvas)
    {
        canvas.SetColor(color)
        canvas.DrawLine(vertex1, vertex2)
        canvas.DrawLine(vertex2, vertex3)
        canvas.DrawLine(vertex3, vertex1)
    }
    override fun toString(): String {
        return "Triangle: color: ${color.name} data: ${vertex1.toString()},  ${vertex2.toString()},  ${vertex3.toString()}"
    }
}