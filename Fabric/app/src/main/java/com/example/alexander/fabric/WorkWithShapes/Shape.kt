package com.example.alexander.fabric.WorkWithShapes

import com.example.alexander.fabric.Definitions.ColorEnum

/**
 * Created by Alexander on 25.06.2016.
 */

abstract class Shape(val color: ColorEnum)
{
    abstract fun Draw(canvas: ICanvas)
}