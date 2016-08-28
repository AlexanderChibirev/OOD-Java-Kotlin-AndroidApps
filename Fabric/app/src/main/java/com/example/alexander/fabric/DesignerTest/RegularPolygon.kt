package com.example.alexander.fabric.DesignerTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.WorkWithShapes.ICanvas
import com.example.alexander.fabric.WorkWithShapes.Shape
import java.util.*

/**
 * Created by Alexander on 25.06.2016.
 */
class RegularPolygon(val center: CordPoint,val vertexCount: Int,val radius: Float, color: ColorEnum):  Shape(color)
{
    private var m_CalcKoef: Double = 1.0
    override fun Draw(canvas: ICanvas)
    {
        val coords = CalcCoords()
        canvas.SetColor(color)
        for (i in 0..vertexCount-2)
        {
            canvas.DrawLine(coords[i], coords[i+1])
        }
        canvas.DrawLine(coords[vertexCount-1], coords[0])
    }

    override fun toString(): String {
        return "RegularPolygon: color: ${color.name} data: ${center.toString()}, ${vertexCount.toString()}, ${radius.toString()}"
    }

    private fun CalcCoords(): ArrayList<CordPoint>
    {
        m_CalcKoef = 2 * Math.PI / vertexCount
        val result = ArrayList<CordPoint>()
        for (i in 0..vertexCount-1)
        {
            result.add(CordPoint(calcX(i), calcY(i)))
        }
        return result
    }

    private fun calcX(i: Int): Float
    {
        return (center.x + radius * Math.cos(i * m_CalcKoef)).toFloat()
    }

    private fun calcY(i: Int): Float
    {
        return (center.x + radius * Math.sin(i * m_CalcKoef)).toFloat()
    }
}