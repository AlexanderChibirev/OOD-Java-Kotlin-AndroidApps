package com.example.alexander.fabric.Interfaces

import com.example.alexander.fabric.WorkWithShapes.PictureDraft
import java.io.InputStream

/**
 * Created by Alexander on 25.06.2016.
 */
interface IDesigner
{
    fun CreateDraft(stream: InputStream): PictureDraft
}