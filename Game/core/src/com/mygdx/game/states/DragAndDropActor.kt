package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image

class DragAndDropActor(imgName: String, initialPosition: Vector3, rect: Rectangle, scoreSuccess: Int, isDisappears: Boolean) : Group() {

    private var dragActor: Actor? = null
    private var dragGroup: Group? = null
    private var touchDown_x: Float = 0f
    private var touchDown_y: Float = 0f
    private var badScoreSound: Sound
    private var successScoreSound: Sound
    private var isDrop = false

    private var dx: Float = 0f
    private var dy: Float = 0f
    private var rectDrag: Rectangle
    private var garbageTexture: Texture
    private var garbageActor: Image
    private var targetDragActor: Image
    private var targetDragTexture: Texture
    private var DragActorBegin: Actor
    private var failDrop = false
    private var score = 0
    private val coordinateDrop = Vector3()
    private var m_initialPosition = Vector3()

    fun isDrop() = isDrop//Explicitly declaring the return type is optional when this can be inferred by the compiler
    fun setDrop(drop: Boolean) {isDrop = drop}
    fun isFailDrop() = failDrop
    fun setFailDrop(drop: Boolean) { failDrop = drop }
    fun getScore() = score
    fun getDropCoordinate() = coordinateDrop
    fun getInitialPosition()=  m_initialPosition

    init {
        badScoreSound = Gdx.audio.newSound(Gdx.files.internal("music//badScore.wav"))
        successScoreSound = Gdx.audio.newSound(Gdx.files.internal("music//s.mp3"))
        score = scoreSuccess
        m_initialPosition = initialPosition
        targetDragTexture = Texture(Gdx.files.internal(imgName))
        targetDragActor = Image(targetDragTexture)
        DragActorBegin = targetDragActor
        DragActorBegin.setOrigin(targetDragActor.width / 2, targetDragActor.height / 2)

        garbageTexture = Texture(Gdx.files.internal(imgName))
        garbageActor = Image(garbageTexture)
        dragActor = garbageActor
        dragActor!!.setOrigin(garbageActor.width / 2, garbageActor.height / 2)


        dragActor!!.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                dragActor!!.addAction(Actions.parallel(
                        Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.swing)//fade выцветание
                ))
                return true
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                dragActor!!.addAction(Actions.parallel(
                        Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.swing)))
            }
        })

        dragGroup = Group()

        dragGroup!!.setPosition(initialPosition.x, initialPosition.y)
        dragGroup!!.setSize(dragActor!!.width, dragActor!!.height)
        dragGroup!!.addActor(dragActor!!)
        rectDrag = Rectangle(-190f, -190f, dragGroup!!.width, dragGroup!!.height)// это кординаты картинки и ее размеры

        dragGroup!!.addListener(object : InputListener() {

            internal val h = dragActor!!.height / 2

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {// когда берешь
                touchDown_x = x
                touchDown_y = h - y
                return true
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {// во время перетаскивания
                dx = dragGroup!!.x - touchDown_x
                dy = dragGroup!!.y - h + touchDown_y
                dragGroup!!.setPosition(x + dx, y + dy)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {//когда отпускаешь
                dx = dragGroup!!.x - touchDown_x
                dy = dragGroup!!.y - h + touchDown_y
                coordinateDrop.set(x + dx, y + dy + dragGroup!!.height, 0f)
                rectDrag.setPosition(x + dx, y + dy)
                if (isDisappears == true)
                // Исчезает если
                {
                    if (rect.overlaps(rectDrag)) { //если поставили на нужное место
                        dragGroup!!.remove()
                        com.mygdx.game.states.PlayState.allScore += scoreSuccess// + прибавляем очки
                        isDrop = true
                        successScoreSound.play()
                    } else {
                        com.mygdx.game.states.PlayState.allScore += -10
                        dragGroup!!.setPosition(initialPosition.x, initialPosition.y)
                        failDrop = true
                        badScoreSound.play()
                    }
                } else {
                    //System.out.println("Enter a number: "+ dx + "    "+ dy);
                    if (rect.overlaps(rectDrag)) { //если поставили на нужное место
                        com.mygdx.game.states.PlayState.allScore += scoreSuccess
                        dragGroup!!.clear()
                        dragGroup!!.setPosition(rect.x, rect.y)
                        dragGroup!!.setSize(DragActorBegin.width, DragActorBegin.height)
                        dragGroup!!.addActor(DragActorBegin)
                        isDrop = true
                        successScoreSound.play()
                    } else {
                        dragGroup!!.setPosition(initialPosition.x, initialPosition.y)
                        com.mygdx.game.states.PlayState.allScore -= 10
                        failDrop = true
                        badScoreSound.play()
                    }
                }
            }
        })

        this.addActor(dragGroup!!)
    }

    internal fun dispose() {
        dragGroup!!.clear()
        dragActor = null
        dragGroup = null
        garbageTexture.dispose()
    }
}
