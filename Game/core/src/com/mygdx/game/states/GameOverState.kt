package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.BeginScreen

/**
 * Created by Alexander on 24.08.2016.
 */
class GameOverState(gsm: GameStateManager) : State(gsm) {

    private val background: Texture
    private val music: Music
    private val playBtn: Texture
    private val startBt: TextureAtlas
    private val startIn: Sprite
    private val startPress: Sprite
    private val playBtnCollision: Rectangle
    private var isPress: Boolean = false
    private val fontScore: BitmapFont

    init {
        background = Texture("retry.png")

        fontScore = BitmapFont()
        fontScore.color = Color.BLACK
        playBtn = Texture("menu//RetryBt.png")
        startBt = TextureAtlas(Gdx.files.internal("menu//RetryBt.txt"))
        startIn = Sprite(startBt.createSprite("start"))
        startPress = Sprite(startBt.createSprite("startPress"))
        playBtnCollision = Rectangle()
        camera.setToOrtho(false, BeginScreen.WIDTH.toFloat(), BeginScreen.HEIGHT.toFloat())

        music = Gdx.audio.newMusic(Gdx.files.internal("music//GameOver.mp3"))
        music.isLooping = true
        music.volume = 0.1f
        music.play()

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
        playBtnCollision.set(490f, 150f, startIn.width, startIn.height)
    }

    override fun render(sb: SpriteBatch) {


        sb.begin()

        sb.projectionMatrix = camera.combined
        sb.draw(background, 0f, 0f, BeginScreen.WIDTH.toFloat(), BeginScreen.HEIGHT.toFloat())
        if (isPress)
            sb.draw(startPress, 490f, 150f)
        else
            sb.draw(startIn, 490f, 150f)
        fontScore.draw(sb, "Your score: " + PlayState.allScore, 550f, 120f)
        sb.end()
    }

    override fun dispose() {
        PlayState.allScore = 0
        background.dispose()
        playBtn.dispose()
        music.dispose()
        fontScore.dispose()
    }
}
