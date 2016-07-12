package com.example.alexander.fabric.ShapesTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.DesignerTest.Ellipse
import com.example.alexander.fabric.DesignerTest.Rectangle
import com.example.alexander.fabric.DesignerTest.RegularPolygon
import com.example.alexander.fabric.DesignerTest.Triangle
import junit.framework.TestCase

/**
 * Created by Alexander on 27.06.2016.
 */




class ShapesTest: TestCase()
{

    val canvas = CanvasForTest()

    fun testRectangle()
    {
        canvas.Reset()
        Rectangle(CordPoint(51f, 43f), CordPoint(0f, 0f), ColorEnum.Red).Draw(canvas)
        assertEquals(canvas.canvasOutput, "line from (51.0,43.0) to (51.0,0.0) color Red\n" +
                "line from (51.0,0.0) to (0.0,0.0) color Red\n" +
                "line from (0.0,0.0) to (0.0,43.0) color Red\n" +
                "line from (0.0,43.0) to (51.0,43.0) color Red\n")
    }

    fun testEllipse()
    {
        canvas.Reset()
        Ellipse(ColorEnum.Blue, CordPoint(1f, 1f), 50f, 80f).Draw(canvas)
        assertEquals(canvas.canvasOutput, "ellipse center CordPoint(x=1.0, y=1.0) 50.0 x 80.0 color Blue\n")
    }

    fun testTriangle()
    {
        canvas.Reset()
        Triangle(CordPoint(300f, 10f), CordPoint(600f, 10f), CordPoint(450f, 400f), ColorEnum.Green).Draw(canvas)
        assertEquals(canvas.canvasOutput, "line from (300.0,10.0) to (600.0,10.0) color Green\n" +
                "line from (600.0,10.0) to (450.0,400.0) color Green\n" +
                "line from (450.0,400.0) to (300.0,10.0) color Green\n")
    }

    fun testPolygon()
    {
        canvas.Reset()
        RegularPolygon(CordPoint(0f, 0f), 5, 5f, ColorEnum.Black).Draw(canvas)
        assertEquals(canvas.canvasOutput, "line from (5.0,0.0) to (1.545085,4.7552824) color Black\n" +
                "line from (1.545085,4.7552824) to (-4.045085,2.9389262) color Black\n" +
                "line from (-4.045085,2.9389262) to (-4.045085,-2.9389262) color Black\n" +
                "line from (-4.045085,-2.9389262) to (1.545085,-4.7552824) color Black\n" +
                "line from (1.545085,-4.7552824) to (5.0,0.0) color Black\n")
    }
}