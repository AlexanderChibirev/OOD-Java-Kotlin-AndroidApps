package com.example.alexander.fabric.WorkWithShapes

import android.graphics.*
import com.example.alexander.fabric.Definitions.ColorEnum
import com.example.alexander.fabric.Definitions.CordPoint
import com.example.alexander.fabric.Interfaces.ICanvas

/**
 * Created by Alexander on 25.06.2016.
 */
class AndroidCanvas() : ICanvas
{

    val mPaint = Paint()

    val mBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
    val mCanvas = Canvas(mBitmap);


    override fun SetColor(color: ColorEnum)
    {
        mPaint.color = when (color)
        {
            ColorEnum.Blue -> Color.BLUE
            ColorEnum.Black -> Color.BLACK
            ColorEnum.Green -> Color.GREEN
            ColorEnum.Pink -> Color.MAGENTA
            ColorEnum.Red -> Color.RED
            ColorEnum.Yellow -> Color.YELLOW
        }
        mPaint.isAntiAlias = true
    }

    override fun DrawLine(from: CordPoint, to: CordPoint)
    {
        mCanvas.drawLine(from.x, from.y, to.x, to.y, mPaint)
    }

    override fun DrawEllipse(center: CordPoint, hRadius: Float, vRadius: Float)
    {
        val rectangle = RectF(center.x - hRadius, center.y - vRadius, center.x + hRadius, center.y + vRadius)
        mCanvas.drawOval(rectangle, mPaint)
    }

    fun getBitmap(): Bitmap = mBitmap
}