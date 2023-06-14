package com.mygdx.game.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Ferramentas.Botao;
import com.mygdx.game.Gerenciador;

public class TelaTransicao extends InputAdapter implements Screen {
    Texture fundo;
    SpriteBatch batch;
    Gerenciador gerenciador;
    Screen tela;
    long inicio;
    BitmapFont font;
    String text;
    final int TEMPOLIMITE = 3;


    public TelaTransicao(Gerenciador gerenciador, Screen tela) {
        fundo = new Texture(Gdx.files.internal("FundoTransicao.png"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        text = String.valueOf(TEMPOLIMITE);
        this.gerenciador = gerenciador;
        this.tela = tela;
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        inicio = System.currentTimeMillis();
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
        renderTempo();
        if (((System.currentTimeMillis() - inicio) / 1000) == 3) {
            gerenciador.setScreen(tela);
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

    public void renderTempo() {
        batch.begin();
        font.getData().setScale(15);
        text = String.valueOf(TEMPOLIMITE - ((System.currentTimeMillis() - inicio) / 1000));
        font.draw(batch, text, 540, Gdx.graphics.getHeight() - 450);
        batch.end();
    }
}
