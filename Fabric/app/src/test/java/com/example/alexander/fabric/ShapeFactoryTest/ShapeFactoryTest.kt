package com.example.alexander.fabric.ShapeFactoryTest

import com.example.alexander.fabric.DesignerTest.Ellipse
import com.example.alexander.fabric.DesignerTest.Rectangle
import com.example.alexander.fabric.DesignerTest.Triangle
import com.example.alexander.fabric.WorkWithShapes.ShapeFactory
import junit.framework.TestCase

/**
 * Created by Alexander on 28.06.2016.
 */
class ShapeFactoryTest: TestCase()
{

    val factory = ShapeFactory()

    val triangleStr = "{ \"shape\": \"triangle\", \"color\": \"Red\", \"data\": [ { \"x\": 1, \"y\": 3 }, { \"x\": 3, \"y\": 3 }, { \"x\": 1, \"y\": 5} ]}"
    val rectangleStr = "{ \"shape\": \"rectangle\", \"color\": \"Blue\", \"data\": [ { \"x\": 1, \"y\": 3 }, { \"x\": 3, \"y\": 3 } ]}"
    val ellipseStr = "{ \"shape\": \"ellipse\", \"color\": \"Black\", \"data\": { \"center\": { \"x\": 3, \"y\": 4 }, \"h_radius\": 3, \"v_radius\": 4}}"
    val polygonStr = "{ \"shape\": \"regular_polygon\", \"color\": \"Red\", \"data\": { \"center\": { \"x\": 3, \"y\": 4 }, \"vertexes\": 8, \"radius\": 4}}"

    fun testCreateTriangle()
    {
        val triangleShape = factory.CreateShape(triangleStr)
        assert(triangleShape is Triangle)
        assertEquals(triangleShape?.toString(), "Triangle: color: Red data: CordPoint(x=1.0, y=3.0),  CordPoint(x=3.0, y=3.0),  CordPoint(x=1.0, y=5.0)")

    }

    fun testCreateEllipse()
    {
        val ellipseShape = factory.CreateShape(ellipseStr)
        assert(ellipseShape is Ellipse)
        assertEquals(ellipseShape?.toString(), "Ellipse: color: Black data: CordPoint(x=3.0, y=4.0), 3.0, 4.0")
    }

    fun testCreateRegularPolygon()
    {
        val ellipseShape = factory.CreateShape(ellipseStr)
        assert(ellipseShape is Ellipse)
        assertEquals(ellipseShape?.toString(),"Ellipse: color: Black data: CordPoint(x=3.0, y=4.0), 3.0, 4.0")
    }

    fun testCreateRectangle()
    {
        val rectangleShape = factory.CreateShape(rectangleStr)
        assert(rectangleShape is Rectangle)
        assertEquals(rectangleShape?.toString(), "Rectangle: color: Blue data: CordPoint(x=1.0, y=3.0), CordPoint(x=3.0, y=3.0)")

    }
}