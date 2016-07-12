package com.example.alexander.fabric.PainterTest

import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.DesignerTest.Ellipse
import com.example.alexander.fabric.DesignerTest.Rectangle
import com.example.alexander.fabric.DesignerTest.RegularPolygon
import com.example.alexander.fabric.DesignerTest.Triangle
import com.example.alexander.fabric.ShapesTest.CanvasForTest
import com.example.alexander.fabric.WorkWithShapes.Painter
import com.example.alexander.fabric.WorkWithShapes.PictureDraft
import junit.framework.TestCase

/**
 * Created by Alexander on 27.06.2016.
 */
class PainterTest :TestCase()
{

    fun testDrawPicture() {
        val draft = PictureDraft()
        draft.AddShape(Ellipse(ColorEnum.Red,CordPoint(100.0f, 400.0f), 50f, 80f))
        draft.AddShape(Rectangle(CordPoint(10f, 5f), CordPoint(200f, 300f), ColorEnum.Blue))
        draft.AddShape(Triangle(CordPoint(300f, 10f), CordPoint(600f, 10f), CordPoint(450f, 400f),ColorEnum.Green))
        draft.AddShape(RegularPolygon(CordPoint(300f, 400f), 8, 100f,ColorEnum.Black))

        val canvasOutputExpected = "ellipse center CordPoint(x=100.0, y=400.0) 50.0 x 80.0 color Red\n"+
                "line from (10.0,5.0) to (10.0,300.0) color Blue\n"+
                "line from (10.0,300.0) to (200.0,300.0) color Blue\n"+
                "line from (200.0,300.0) to (200.0,5.0) color Blue\n"+
                "line from (200.0,5.0) to (10.0,5.0) color Blue\n"+
                "line from (300.0,10.0) to (600.0,10.0) color Green\n"+
                "line from (600.0,10.0) to (450.0,400.0) color Green\n"+
                "line from (450.0,400.0) to (300.0,10.0) color Green\n"+
                "line from (400.0,300.0) to (370.7107,370.7107) color Black\n"+
                "line from (370.7107,370.7107) to (300.0,400.0) color Black\n"+
                "line from (300.0,400.0) to (229.28932,370.7107) color Black\n"+
                "line from (229.28932,370.7107) to (200.0,300.0) color Black\n"+
                "line from (200.0,300.0) to (229.28932,229.28932) color Black\n"+
                "line from (229.28932,229.28932) to (300.0,200.0) color Black\n"+
                "line from (300.0,200.0) to (370.7107,229.28932) color Black\n"+
                "line from (370.7107,229.28932) to (400.0,300.0) color Black\n"

        val painter = Painter()
        val canvas = CanvasForTest()

        painter.DrawPicture(draft, canvas)
        assertEquals(canvas.canvasOutput, canvasOutputExpected)
    }
}