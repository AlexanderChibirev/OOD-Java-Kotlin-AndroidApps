package com.example.alexander.fabric

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alexander.fabric.WorkWithShapes.AndroidCanvas
import com.example.alexander.fabric.WorkWithShapes.Designer
import com.example.alexander.fabric.WorkWithShapes.Painter
import com.example.alexander.fabric.WorkWithShapes.ShapeFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream



class MainActivity : AppCompatActivity()
{

    val m_designer = Designer(ShapeFactory())
    val m_painter = Painter()
    val m_canvas = AndroidCanvas()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { loadAndDraw("shapes.txt") }

    }

    fun loadAndDraw(filename: String)
    {
        drawPicture(assets.open(filename))
    }

    fun drawPicture(stream: InputStream)
    {
        m_painter.DrawPicture(m_designer.CreateDraft(stream), m_canvas)
        imageView.setImageBitmap(m_canvas.getBitmap())
        imageView.invalidate()
    }
}