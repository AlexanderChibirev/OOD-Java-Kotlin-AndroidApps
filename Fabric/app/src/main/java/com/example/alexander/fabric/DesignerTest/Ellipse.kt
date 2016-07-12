package com.example.alexander.fabric.DesignerTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.Interfaces.ICanvas
import com.example.alexander.fabric.Interfaces.Shape

/**
 * Created by Alexander on 25.06.2016.
 */
class Ellipse(color: ColorEnum, var center: CordPoint, var hRadius: Float, var wRadius: Float): Shape(color)
{
    override fun Draw(canvas: ICanvas)
    {
        canvas.SetColor(color)
        canvas.DrawEllipse(center, hRadius, wRadius)
    }
    override fun toString(): String
    {
        return "Ellipse: color: ${color.name} data: ${center.toString()}, ${hRadius.toString()}, ${wRadius.toString()}"
    }
}
