package com.example.alexander.fabric.WorkWithShapes

import com.example.alexander.fabric.Interfaces.IDesigner
import com.example.alexander.fabric.Interfaces.IShapeFactory
import java.io.InputStream

/**
 * Created by Alexander on 25.06.2016.
 */
class Designer(val shapeFactory: IShapeFactory): IDesigner
{
    override fun CreateDraft(stream: InputStream): PictureDraft
    {
        val pictureDraft = PictureDraft()
        stream.reader().forEachLine {
            val shape = shapeFactory.CreateShape(it)
            if (shape != null)
                pictureDraft.AddShape(shape)
        }
        return pictureDraft
    }
}