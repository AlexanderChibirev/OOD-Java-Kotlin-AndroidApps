package com.mygdx.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array
import com.mygdx.game.BeginScreen

/**
 * Created by Alexander on 24.08.2016.
 */
class PlayState(gsm: GameStateManager) : State(gsm), InputProcessor {

    internal var music: Music
    internal var stage: Stage
    private val bg: Texture
    private val twentyScore: Texture
    private val tenScore: Texture
    private val fiftyScore: Texture
    private val bucketUp: Texture
    private val bucketAll: Texture
    private var countObjects = 0//27
    private var isScore = false
    private var timeScore = 10000f
    private var dxScore = 0f
    private var dyScore = 0f
    private val bucketAnimation: Animation
    private var scoreOb = 0
    private val bucketTargetDropCoordinate = Rectangle(30f, 40f, 20f, 20f)
    private var coordinateScore = Vector3()
    private var fontScore: BitmapFont? = null
    private var secondTime = 60f
    private var minTime = 1
    private val dragAndDropObjects: Array<DragAndDropActor>

    init {

        music = Gdx.audio.newMusic(Gdx.files.internal("music//GameProcess.mp3"))
        music.isLooping = true
        music.play()

        bucketUp = Texture("bucketUp.png")
        bucketAll = Texture("bucketAll.png")
        twentyScore = Texture("twentyScore.png")
        fiftyScore = Texture("fiftyScore.png")
        tenScore = Texture("tenScore.png")
        bg = Texture("bgGame.jpg")

        dragAndDropObjects = Array<DragAndDropActor>()
        bucketAnimation = Animation(TextureRegion(bucketAll), 10, 0.5f)


        stage = Stage()

        addDragAndDropActorObjects()
        addActorForStage()

        Gdx.input.inputProcessor = stage
        initialFont()
    }

    private fun addDragAndDropActorObjects() {
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage1.png", Vector3(450f, 0f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage1.png", Vector3(350f, 0f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage1.png", Vector3(650f, 70f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage1.png", Vector3(150f, 1f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage1.png", Vector3(200f, 34f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage2.png", Vector3(100f, 20f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage3.png", Vector3(300f, 70f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage4.png", Vector3(350f, 80f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage4.png", Vector3(650f, 50f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage4.png", Vector3(690f, 30f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage4.png", Vector3(350f, 20f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage4.png", Vector3(120f, 260f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage2.png", Vector3(520f, 70f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage3.png", Vector3(620f, 10f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage5.png", Vector3(720f, 70f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage5.png", Vector3(160f, 230f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage6.png", Vector3(225f, 70f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage7.png", Vector3(780f, 410f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage6.png", Vector3(375f, 370f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("lumpOfGarbage7.png", Vector3(430f, 200f, 0f), bucketTargetDropCoordinate, 20, true))
        dragAndDropObjects.add(DragAndDropActor("alarmClock.png", Vector3(30f, 235f, 0f), Rectangle(278f, 318f, 10f, 10f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("clock.png", Vector3(330f, 100f, 0f), Rectangle(290f, 375f, 80f, 80f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("stool.png", Vector3(625f, 70f, 0f), Rectangle(125f, 70f, 50f, 50f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("flower.png", Vector3(530f, 205f, 0f), Rectangle(750f, 275f, 20f, 20f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("pillow.png", Vector3(350f, 70f, 0f), Rectangle(460f, 205f, 40f, 40f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("picture.png", Vector3(260f, 100f, 0f), Rectangle(760f, 215f, 10f, 10f), 50, false))
        dragAndDropObjects.add(DragAndDropActor("clip.png", Vector3(0f, 0f, 0f), Rectangle(25f, 183f, 30f, 30f), 50, false))
    }

    private fun addActorForStage() {
        for (objectDrag in dragAndDropObjects) {
            stage.addActor(objectDrag)
        }
    }

    private fun initialFont() {
        fontScore = BitmapFont()
        fontScore!!.color = Color.BLACK
    }

    override fun handleInput() {

    }

    private fun updateForTimer() {
        val speed = 0.02f//0.02 для норм, 1.5 для проверки
        if (secondTime < 1) {
            secondTime = 59f
            minTime -= 1
        }
        secondTime = secondTime - speed
        if (minTime < 0) {
            gsm.set(GameOverState(gsm))
            music.stop()
        }
    }

    fun updateForAnimScore() {
        if (isScore) {
            dxScore += 0.1f
            dyScore += 1.5f
            timeScore += 1f
        }
        if (timeScore > 20) {
            timeScore = 0f
            isScore = false
        }
    }

    override fun update(dt: Float) {
        bucketAnimation.update(dt)
        updateForAnimScore()
        //////////////////////////////////
        if (countObjects == dragAndDropObjects.size) {
            music.stop()
            gsm.set(PlayState(gsm))
        }
        updateForTimer()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(bg, 0f, 0f, BeginScreen.WIDTH.toFloat(), BeginScreen.HEIGHT.toFloat())
        if (secondTime.toInt() < 10) {
            fontScore!!.draw(sb,  "$minTime:0" + secondTime.toInt(), 155f, 450f)
        } else {
            fontScore!!.draw(sb, "$minTime:" + secondTime.toInt(), 155f, 450f)
        }
        fontScore!!.draw(sb, "1", 280f, 450f)
        ///////////////////////////////
        if (allScore >= 100) {
            fontScore!!.draw(sb, "" + allScore, 375f, 450f)
        } else {
            fontScore!!.draw(sb, "" + allScore, 385f, 450f)
        }
        if (!isScore || scoreOb == -10 || scoreOb == 50) {
            sb.draw(bucketUp, 0f, 0f)
        }
        //////////////////////////////
        sb.end()
        stage.act()
        stage.draw()
        sb.begin()
        if (isScore) {
            if (scoreOb == 20) {
                sb.draw(bucketAnimation.getFrame(), 0f, 0f)
                sb.draw(twentyScore, dxScore, dyScore)
            }
            else if (scoreOb == 50) { sb.draw(fiftyScore, dxScore, dyScore)}
            else { sb.draw(tenScore, dxScore, dyScore) }
        }
        else {
            scoreOb = -10
        }
        for (objectDrop in dragAndDropObjects)
        {
            if (objectDrop.isFailDrop()) {
                coordinateScore = objectDrop.getInitialPosition()
                setDataForScore(objectDrop)
                objectDrop.setFailDrop(false)

            }
            if (objectDrop.isDrop()) {
                countObjects++
                objectDrop.setDrop(false)
                coordinateScore = objectDrop.getDropCoordinate()
                setDataForScore(objectDrop)
                if (objectDrop.getScore() == 20) {
                    scoreOb = 20
                }
                if (objectDrop.getScore() == 50) {
                    scoreOb = 50
                }
            }
        }
        sb.end()
    }

    private fun setDataForScore(objects: DragAndDropActor) {
        dxScore = coordinateScore.x
        dyScore = coordinateScore.y
        isScore = true
    }

    override fun dispose() {
        stage.dispose()
        music.dispose()
        bg.dispose()
        twentyScore.dispose()
        tenScore.dispose()
        fiftyScore.dispose()
        bucketUp.dispose()
        bucketAll.dispose()
        fontScore!!.dispose()
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    companion object { //
        internal var allScore: Int = 0
    }
}
