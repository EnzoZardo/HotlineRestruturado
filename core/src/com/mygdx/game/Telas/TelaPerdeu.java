package com.mygdx.game.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Ferramentas.Botao;
import com.mygdx.game.Gerenciador;

public class TelaPerdeu extends InputAdapter implements Screen {
    Texture fundo;
    SpriteBatch batch;
    Botao botaoVoltar;
    Gerenciador gerenciador;

    public TelaPerdeu(Gerenciador gerenciador) {
        fundo = new Texture(Gdx.files.internal("FundoPerdeu.png"));
        batch = new SpriteBatch();
        botaoVoltar = new Botao((Gdx.graphics.getWidth() - 288) / 2, (Gdx.graphics.getHeight() - 230) / 2, 263, 93, 214/255f, 68/255f, 179/255f, "Voltar");
        this.gerenciador = gerenciador;
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        batch.begin();
        batch.draw(fundo, 0, 0);
        batch.end();
        botaoVoltar.update();
        if (botaoVoltar.clicou() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gerenciador.setScreen(new TelaMenu(gerenciador));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fundo.dispose();
    }
}
