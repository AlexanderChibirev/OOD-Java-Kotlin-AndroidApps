package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.BeginScreen;

/**
 * Created by Alexander on 24.08.2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bgMenu.jpg");
        playBtn = new Texture("playbtn.png");




    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new com.mygdx.game.states.PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, BeginScreen.WIDTH, BeginScreen.HEIGHT);
        sb.draw(playBtn, (BeginScreen.WIDTH / 2) - (playBtn.getWidth() / 2), BeginScreen.HEIGHT / 6);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
