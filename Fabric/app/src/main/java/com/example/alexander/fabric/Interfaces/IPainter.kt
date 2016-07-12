package com.example.alexander.fabric.Interfaces

import com.example.alexander.fabric.WorkWithShapes.PictureDraft

/**
 * Created by Alexander on 25.06.2016.
 */
interface IPainter
{
    fun DrawPicture(draft: PictureDraft, canvas: ICanvas)
}