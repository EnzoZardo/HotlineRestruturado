package com.mygdx.game.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entidades.Vilao;
import com.mygdx.game.Estados.Estados;
import com.mygdx.game.Gerenciador;

public class TelaFase1 extends InputAdapter implements Screen {
    Estados estados;
    Gerenciador gerenciador;
    BitmapFont font;
    String text = "Fase 1";
    SpriteBatch batch;

    public TelaFase1(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
        estados = new Estados("core/src/com/mygdx/game/Mapas/fase1.txt");
        estados.populaPlanta();
        batch = new SpriteBatch();
        font = new BitmapFont();
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Crosshair);
    }

    public void finalizacao() {
        if (!estados.heroi.getEstaVivo()) {
            Gerenciador.resetaGerenciador();
            gerenciador.setScreen(new TelaPerdeu(gerenciador));
        }
    }

    @Override
    public void show() {

    }

    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.4f, 0.5f, 0.4f, 1);
        estados.renderRetangulos();
        estados.renderViloes();
        estados.getHeroi().render(estados);
        estados.getArma().render(estados);
        estados.renderProjeteis();
        estados.renderParedes();
        estados.renderPontos();
        renderFase();
        passaFase();
        finalizacao();
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
        estados.getHeroi().getImg().dispose();
        estados.getArma().getImg().dispose();
        estados.disposeViloes();
        estados.disposeProjeteis();
        estados.disposeParedes();
    }

    public void renderFase() {
        batch.begin();
        font.getData().setScale(2);
        font.draw(batch, text, 20, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    public void passaFase() {
        int contMortos = 0;
        for (Vilao vilao : estados.viloes) {
            if (!vilao.isEstaVivo()) {
                contMortos++;
            }
        }
        if (contMortos == estados.viloes.size) {
            gerenciador.setScreen(new TelaTransicao(gerenciador, new TelaFase2(gerenciador)));
        }
    }
}
