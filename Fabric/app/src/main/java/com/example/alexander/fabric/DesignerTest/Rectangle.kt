package com.example.alexander.fabric.DesignerTest
import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.Interfaces.ICanvas
import com.example.alexander.fabric.Interfaces.Shape

/**
 * Created by Alexander on 25.06.2016.
 */
class Rectangle(var leftTop: CordPoint, val rightBottom: CordPoint, color : ColorEnum) : Shape(color)
{
    override fun Draw(canvas: ICanvas)
    {
        val x1 = leftTop.x
        val y1 = leftTop.y
        val x2 = rightBottom.x
        val y2 = rightBottom.y
        canvas.SetColor(color)
        canvas.DrawLine(CordPoint(x1, y1), CordPoint(x1, y2))
        canvas.DrawLine(CordPoint(x1, y2), CordPoint(x2, y2))
        canvas.DrawLine(CordPoint(x2, y2), CordPoint(x2, y1))
        canvas.DrawLine(CordPoint(x2, y1), CordPoint(x1, y1))
    }
    override fun toString(): String {
        return "Rectangle: color: ${color.name} data: ${leftTop.toString()}, ${rightBottom.toString()}"
    }
}