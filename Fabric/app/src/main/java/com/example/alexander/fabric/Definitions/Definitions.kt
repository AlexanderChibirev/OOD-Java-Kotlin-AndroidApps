package com.example.alexander.fabric.Definitions

/**
 * Created by Alexander on 25.06.2016.
 */
enum class ColorEnum
{
    Green,
    Red,
    Blue,
    Yellow,
    Pink,
    Black
}

enum class ShapeType
{
    rectangle,
    triangle,
    ellipse,
    regular_polygon
}

data class CordPoint(val x: Float,val y: Float)//специальный класс, для хранения данных, компилятор сам преобразует