package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.BeginScreen;

/**
 * Created by Alexander on 24.08.2016.
 */
public class GameOverState extends State  {

    private Texture background;
    private Music music;
    private Texture playBtn;
    private TextureAtlas startBt;
    private Sprite startIn;
    private Sprite startPress;
    private Rectangle playBtnCollision;
    private boolean isPress;
    private BitmapFont fontScore;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("retry.png");

        fontScore = new BitmapFont();
        fontScore.setColor(Color.BLACK);
        playBtn = new Texture("menu//playbtn.png");
        startBt = new TextureAtlas(Gdx.files.internal("menu//StartBt.txt"));
        startIn = new Sprite(startBt.createSprite("start"));
        startPress = new Sprite(startBt.createSprite("startPress"));
        playBtnCollision = new Rectangle();
        camera.setToOrtho(false, BeginScreen.WIDTH, BeginScreen.HEIGHT);

        music = Gdx.audio.newMusic(Gdx.files.internal("music//GameOver.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

    }
    @Override
    protected void handleInput() {
        isPress = false;
        if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (playBtnCollision.contains(touchPos.x, touchPos.y)) {
                isPress = true;
            }
        }
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (playBtnCollision.contains(touchPos.x, touchPos.y)) {
                gsm.set(new PlayState(gsm));
                music.stop();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        playBtnCollision.set(490, 150, startIn.getWidth(), startIn.getHeight());
    }

    @Override
    public void render(SpriteBatch sb) {



        sb.begin();

        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, 0, 0, BeginScreen.WIDTH, BeginScreen.HEIGHT);
        if (isPress)
            sb.draw(startPress, 490, 150);
        else
            sb.draw(startIn, 490, 150);
        fontScore.draw(sb, "Your score: "+PlayState.allScore, 550, 120);
        sb.end();
    }

    @Override
    public void dispose() {
        PlayState.allScore = 0;
        background.dispose();
        playBtn.dispose();
        music.dispose();
        fontScore.dispose();
    }
}
