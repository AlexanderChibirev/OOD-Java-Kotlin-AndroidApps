package com.example.alexander.fabric.WorkWithShapes

import com.example.alexander.fabric.Interfaces.ICanvas
import com.example.alexander.fabric.Interfaces.IPainter

/**
 * Created by Alexander on 25.06.2016.
 */
class Painter : IPainter {
    override fun DrawPicture(draft: PictureDraft, canvas: ICanvas)
    {
        for (i in 0..(draft.GetShapeCount() - 1))
        {
            draft.GetShape(i).Draw(canvas)
        }
    }
}
