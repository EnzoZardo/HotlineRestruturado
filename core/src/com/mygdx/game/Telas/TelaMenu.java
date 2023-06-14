package com.mygdx.game.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Ferramentas.Botao;
import com.mygdx.game.Gerenciador;

import javax.swing.*;
import java.io.FileNotFoundException;

public class TelaMenu extends InputAdapter implements Screen {
    Texture fundo;
    SpriteBatch batch;
    Botao botaoIniciar, botaoGrafico;
    Gerenciador gerenciador;

    public TelaMenu(Gerenciador gerenciador) {
        fundo = new Texture(Gdx.files.internal("will.png"));
        batch = new SpriteBatch();
        botaoIniciar = new Botao((Gdx.graphics.getWidth() - 288) / 2, (Gdx.graphics.getHeight() - 230) / 2, 263, 93, 214/255f, 68/255f, 179/255f, "Início");
        botaoGrafico = new Botao((Gdx.graphics.getWidth() - 288) / 2, (Gdx.graphics.getHeight() - 500) / 2, 263, 93, 214/255f, 68/255f, 179/255f, "Gráfico");
        this.gerenciador = gerenciador;
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
        botaoIniciar.update();
        botaoGrafico.update();
        if (botaoIniciar.clicou()) {
            gerenciador.setScreen(new TelaNome(gerenciador));
        }
        if (botaoGrafico.clicou()) {
            try {
                (new TelaGrafico()).setVisible(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
