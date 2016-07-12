package com.example.alexander.fabric.WorkWithShapes
import com.example.alexander.fabric.Interfaces.Shape
import java.util.*

/**
 * Created by Alexander on 25.06.2016.
 */
class PictureDraft {
    val shapeList = ArrayList<Shape>()

    fun AddShape(shape: Shape)
    {
        shapeList.add(shape)
        //var x = Pair(1,2)
    }

    fun GetShapeCount(): Int
    {
        return shapeList.size
    }

    fun GetShape(idx: Int): Shape
    {
        return shapeList[idx]
    }
}