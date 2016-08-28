package com.example.alexander.fabric.ShapesTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.WorkWithShapes.ICanvas

/**
 * Created by Alexander on 27.06.2016.
 */
class CanvasForTest : ICanvas {

    private lateinit var mColor: ColorEnum
    lateinit var canvasOutput: String
    init
    {
        Reset()
    }
    fun Reset()
    {
        mColor = ColorEnum.Black
        canvasOutput = ""
    }
    override fun SetColor(color: ColorEnum)
    {
        mColor = color
    }
    override fun DrawLine(from: CordPoint, to: CordPoint)
    {
        canvasOutput += "line from (${from.x},${from.y}) to (${to.x},${to.y}) color ${mColor.name}\n"
    }
    override fun DrawEllipse(center: CordPoint, hRadius: Float, vRadius: Float)
    {
        canvasOutput += "ellipse center ${center.toString()} $hRadius x $vRadius color ${mColor.name}\n"
    }
}
