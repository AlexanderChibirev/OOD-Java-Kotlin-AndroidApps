package com.example.alexander.fabric.DesignerTest


import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.WorkWithShapes.Designer
import com.example.alexander.fabric.WorkWithShapes.PictureDraft
import com.example.alexander.fabric.WorkWithShapes.ShapeFactory
import junit.framework.TestCase

/**
 * Created by Alexander on 26.06.2016.
 */
class DesignerTest : TestCase()
{

    val pictureStream: String = "{ \"shape\": \"ellipse\", \"color\": \"Red\", \"data\": { \"center\": { \"x\": \"100\", \"y\": \"400\" }, \"h_radius\": 50, \"v_radius\":80 }}\n"+
            "{ \"shape\": \"rectangle\", \"color\": \"Blue\", \"data\": [ { \"x\": 10, \"y\": 5 }, { \"x\": 200, \"y\": 300 } ]}\n"+
            "{ \"shape\": \"triangle\", \"color\": \"Green\", \"data\": [ { \"x\": 300, \"y\": 10 }, { \"x\": 600, \"y\": 10 }, { \"x\": 450, \"y\": 400} ]}\n"+
            "{ \"shape\": \"regular_polygon\", \"color\": \"Black\", \"data\": { \"center\": { \"x\": 300, \"y\": 400 }, \"vertexes\": 8, \"radius\": 100}}\n"

    fun testCreateDraft() {
        val designer = Designer(ShapeFactory())
        val draft = designer.CreateDraft(pictureStream.byteInputStream())
        val draftExpected = PictureDraft()
        //  class Ellipse(color: ColorEnum, var center: CordPoint, var hRadius: Float, var wRadius: Float): Shape(color)
        //class Rectangle(var leftTop: CordPoint, val rightBottom: CordPoint, color : ColorEnum) : Shape(color)
        //class RegularPolygon(val center: CordPoint,val vertexCount: Int,val radius: Float, color: ColorEnum):  Shape(color)
        draftExpected.AddShape(Ellipse(ColorEnum.Red,CordPoint(100.0f, 400.0f), 50f, 80f))
        draftExpected.AddShape(Rectangle(CordPoint(10f, 5f), CordPoint(200f, 300f), ColorEnum.Blue))
        draftExpected.AddShape(Triangle(CordPoint(300f, 10f), CordPoint(600f, 10f), CordPoint(450f, 400f),ColorEnum.Green))
        draftExpected.AddShape(RegularPolygon(CordPoint(300f, 400f), 8, 100f,ColorEnum.Black))
        assertEquals(draft.GetShapeCount(), draftExpected.GetShapeCount())
        for (i in 0..(draft.GetShapeCount() - 1)) {
            val shape = draft.GetShape(i)
            val shapeExpected = draftExpected.GetShape(i)
           assertEquals(shape.toString(), shapeExpected.toString())
        }
    }
}

