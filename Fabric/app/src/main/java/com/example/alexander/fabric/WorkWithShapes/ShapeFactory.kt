package com.example.alexander.fabric.WorkWithShapes


import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.Definitions.ShapeType
import com.example.alexander.fabric.DesignerTest.Ellipse
import com.example.alexander.fabric.DesignerTest.Rectangle
import com.example.alexander.fabric.DesignerTest.RegularPolygon
import com.example.alexander.fabric.DesignerTest.Triangle
import com.example.alexander.fabric.Interfaces.IShapeFactory
import com.example.alexander.fabric.Interfaces.Shape
import com.github.salomonbrys.kotson.get
import com.google.gson.JsonElement
import com.google.gson.JsonParser

/**
 * Created by Alexander on 25.06.2016.
 */
class ShapeFactory : IShapeFactory
{

    override fun CreateShape(description: String): Shape?
    {
        val jsonObj = JsonParser().parse(description)
        val shapeType = ShapeType.valueOf(jsonObj["shape"].asString)//получаем по стрингу значение метод в enum
        val color = ColorEnum.valueOf(jsonObj["color"].asString)//получаем по стрингу значение
        val data = jsonObj["data"]

        return when (shapeType) {
            ShapeType.rectangle -> GetRectangle(color, data)
            ShapeType.ellipse -> GetEllipse(color, data)
            ShapeType.regular_polygon -> GetRegularPolygon(color, data)
            ShapeType.triangle -> GetTriangle(color, data)
            else -> null
        }
    }
    private fun GetRectangle(color: ColorEnum ,json: JsonElement): Rectangle
    {
        val jsonArray = json.asJsonArray
        val leftTop = CordPoint(jsonArray[0]["x"].asFloat, jsonArray[0]["y"].asFloat)
        val rightBottom = CordPoint(jsonArray[1]["x"].asFloat, jsonArray[1]["y"].asFloat)
        return Rectangle(leftTop, rightBottom, color)
    }

    private fun GetEllipse(color: ColorEnum ,json: JsonElement): Ellipse
    {
        val centerJsonObject = json["center"].asJsonObject
        val center = CordPoint(centerJsonObject["x"].asFloat, centerJsonObject["y"].asFloat)
        return Ellipse(color, center, json["h_radius"].asFloat, json["v_radius"].asFloat)
    }

    private fun GetRegularPolygon(color: ColorEnum ,json: JsonElement): RegularPolygon
    {
        val centerJsonObject = json["center"].asJsonObject
        val center = CordPoint(centerJsonObject["x"].asFloat, centerJsonObject["y"].asFloat)
        return RegularPolygon(center,json["vertexes"].asInt,json["radius"].asFloat,color)
    }

    private fun GetTriangle(color: ColorEnum ,json: JsonElement): Triangle
    {
        val jsonArray = json.asJsonArray
        val vertex1 = CordPoint(jsonArray[0]["x"].asFloat, jsonArray[0]["y"].asFloat)
        val vertex2 = CordPoint(jsonArray[1]["x"].asFloat, jsonArray[1]["y"].asFloat)
        val vertex3 = CordPoint(jsonArray[2]["x"].asFloat, jsonArray[2]["y"].asFloat)
        return Triangle(vertex1, vertex2, vertex3, color )
    }
}