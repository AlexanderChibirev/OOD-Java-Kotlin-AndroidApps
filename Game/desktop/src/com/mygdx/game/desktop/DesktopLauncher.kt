package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.BeginScreen

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = BeginScreen.WIDTH
        config.height = BeginScreen.HEIGHT
        config.title = BeginScreen.TITLE
        LwjglApplication(BeginScreen(), config)
    }
}
