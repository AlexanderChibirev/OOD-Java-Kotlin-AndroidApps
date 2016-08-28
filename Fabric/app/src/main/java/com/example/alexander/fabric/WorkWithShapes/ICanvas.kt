package com.example.alexander.fabric.WorkWithShapes

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint

/**
 * Created by Alexander on 25.06.2016.
 */
interface ICanvas
{
    fun SetColor(color: ColorEnum)
    fun DrawLine(from: CordPoint, to: CordPoint)
    fun DrawEllipse(center: CordPoint, hRadius: Float, vRadius: Float)
}