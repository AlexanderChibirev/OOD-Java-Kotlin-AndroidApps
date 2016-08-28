package com.example.alexander.fabric.WorkWithShapes

/**
 * Created by Alexander on 25.06.2016.
 */
interface IShapeFactory
{
    fun CreateShape(description: String): Shape?
}