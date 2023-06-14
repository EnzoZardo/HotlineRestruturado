package com.mygdx.game.Telas;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arquivos.Arquivos;
import com.mygdx.game.Ferramentas.Botao;
import com.mygdx.game.Gerenciador;

import java.io.IOException;
import java.util.HashMap;

public class TelaNome extends InputAdapter implements Screen {
    final int MAXLETRAS = 12;
    Texture fundo;
    SpriteBatch batch;
    BitmapFont font;
    Botao botaoIniciar;
    Gerenciador gerenciador;
    HashMap<Integer, String> teclasAceitas;
    Color corNome;

    public TelaNome(Gerenciador gerenciador) {
        fundo = new Texture(Gdx.files.internal("FundoNome.png"));
        font = new BitmapFont();
        batch = new SpriteBatch();
        corNome = new Color();
        corNome.set(0, 0, 0, 1);
        teclasAceitas = new HashMap<>();
        populaMapa();
        botaoIniciar = new Botao((Gdx.graphics.getWidth() - 288) / 2, (Gdx.graphics.getHeight() - 230) / 2, 263, 93, 214/255f, 68/255f, 179/255f, "Jogar");
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
        botaoIniciar.update();
        if ((botaoIniciar.clicou() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) && Gerenciador.Nome.length() > 0) {
            gerenciador.setScreen(new TelaFase1(gerenciador));
        }
        digita();
        renderNome();
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

    public void digita() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && Gerenciador.Nome.length() > 0) {
            Gerenciador.Nome = Gerenciador.Nome.substring(0, Gerenciador.Nome.length() - 1);
        }
        for (int chave : teclasAceitas.keySet()) {
            if (Gdx.input.isKeyJustPressed(chave) && Gerenciador.Nome.length() < MAXLETRAS) {
                Gerenciador.Nome += teclasAceitas.get(chave);
            }
        }
    }

    public void renderNome() {
        batch.begin();
        font.setColor(corNome);
        font.getData().setScale(3);
        String text = Gerenciador.Nome;
        font.draw(batch, text, 320, Gdx.graphics.getHeight() - 260);
        batch.end();
    }

    public void populaMapa() {
        for (int i = Input.Keys.A, charCode = 65; i <= Input.Keys.Z; i++, charCode++) {
            teclasAceitas.put(i, String.valueOf((char) charCode));
        }
    }
}
