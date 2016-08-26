package com.mygdx.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.BeginScreen

/**
 * Created by Alexander on 24.08.2016.
 */
class MenuState(gsm: GameStateManager) : State(gsm) {

    private val background: Texture
    private val music: Music

    private val startBt: TextureAtlas
    private val startIn: Sprite
    private val startPress: Sprite
    private val playBtnCollision: Rectangle
    private var isPress: Boolean = false


    init {
        background = Texture("bgMenu.jpg")

        music = Gdx.audio.newMusic(Gdx.files.internal("music//GameMenuAndRetry.mp3"))
        music.isLooping = true
        music.volume = 0.1f
        music.play()

        startBt = TextureAtlas(Gdx.files.internal("menu//StartBt.txt"))
        startIn = Sprite(startBt.createSprite("start"))
        startPress = Sprite(startBt.createSprite("startPress"))
        playBtnCollision = Rectangle()
        camera.setToOrtho(false, BeginScreen.WIDTH.toFloat(), BeginScreen.HEIGHT.toFloat())

    }

    override fun handleInput() {

        isPress = false
        if (Gdx.input.isTouched) {
            val touchPos = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)
            if (playBtnCollision.contains(touchPos.x, touchPos.y)) {
                isPress = true
            }
        }
        if (Gdx.input.justTouched()) {
            val touchPos = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)
            if (playBtnCollision.contains(touchPos.x, touchPos.y)) {
                gsm.set(PlayState(gsm))
                music.stop()
            }
        }
    }

    override fun update(dt: Float) {
        handleInput()
        playBtnCollision.set((BeginScreen.WIDTH / 2).toFloat(), (BeginScreen.HEIGHT / 6).toFloat(), startIn.width, startIn.height)
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.projectionMatrix = camera.combined
        sb.draw(background, 0f, 0f, BeginScreen.WIDTH.toFloat(), BeginScreen.HEIGHT.toFloat())
        if (isPress)
            sb.draw(startPress, (BeginScreen.WIDTH / 2).toFloat(), (BeginScreen.HEIGHT / 6).toFloat())
        else
            sb.draw(startIn, (BeginScreen.WIDTH / 2).toFloat(), (BeginScreen.HEIGHT / 6).toFloat())
        sb.end()
    }

    override fun dispose() {
        background.dispose()
        music.dispose()
        startBt.dispose()

    }
}
