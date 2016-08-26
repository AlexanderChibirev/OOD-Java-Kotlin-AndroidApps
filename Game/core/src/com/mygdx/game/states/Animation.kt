package com.mygdx.game.states

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

/**
 * Created by Alexander on 25.08.2016.
 */
class Animation(region: TextureRegion, private val frameCount: Int, cycleTime: Float) {
    private val frames: Array<TextureRegion>
    private val maxFrameTime: Float
    private var currentFrameTime: Float = 0.toFloat()
    private var frame: Int = 0

    init {
        frames = Array<TextureRegion>()
        val frameWidth = region.regionWidth / frameCount
        for (i in 0..frameCount - 1) {
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }
        maxFrameTime = cycleTime / frameCount
        frame = 0
    }

    fun update(dt: Float) {
        currentFrameTime += dt
        if (currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount)
            frame = 0
    }

    fun getFrame(): TextureRegion {
        return frames.get(frame)
    }
}