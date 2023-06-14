package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Estados.Estados;

public class Arma extends Entidade {
    private static final String NOMEIMAGEM = "arma.png";
    private int tiros = 60;
    private BitmapFont font;
    private String text = "Tiros: ";
    private SpriteBatch batch;

    public Arma(int x, int y, int width, int height) {
        super(x, y, width, height, NOMEIMAGEM);
        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    public void atirar(Estados estados) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && tiros > 0) {
            estados.projeteis.add(new Projetil((int)(estados.heroi.posicao.x + estados.heroi.entidade.width/2), (int)(estados.heroi.posicao.y + estados.heroi.entidade.height/2), 1, 1, alpha));
            tiros--;
        }
    }

    public int getTiros() {
        return tiros;
    }

    @Override
    public void move(Estados estados) {
        posicao.x = estados.heroi.posicao.x + ((int) estados.heroi.entidade.width >> 2) + (entidade.width);
        posicao.y = estados.heroi.posicao.y + ((int) estados.heroi.entidade.height >> 2) - (entidade.height / 2);
        gira(estados);
    }

    @Override
    public void gira(Estados estados) {
        double catetoAdjacente = (Gdx.input.getX() - posicao.x) ;
        double catetoOposto = (Gdx.graphics.getHeight() - Gdx.input.getY() - posicao.y);
        double myAtan = Math.atan(catetoOposto / catetoAdjacente) * 180 / Math.PI;
        alpha = (float) (Gdx.input.getX() < posicao.x? 270 + myAtan: 90 + myAtan);
    }

    @Override
    public void render(Estados estados) {
        move(estados);
        atirar(estados);
        batch.begin();
        font.getData().setScale(2);
        font.draw(batch, text + tiros, 200, Gdx.graphics.getHeight() - 20);
        batch.end();
        super.render(estados);
    }
}
