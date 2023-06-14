package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Estados.Estados;
import com.mygdx.game.Ferramentas.Retangulo;

public class Heroi extends Entidade {
    private static final String NOMEIMAGEM = "heroi.png";
    private boolean estaVivo;
    private int vida = 100;
    private Retangulo vidaRetangulo;
    private final int MAXVIDA = 100, DANO = 1,offsetVidaY = -40;

    public Heroi(int x, int y, int width, int height) {
        super(x, y, width, height, NOMEIMAGEM);
        setVELOCIDADE(15);
        estaVivo = true;
        vidaRetangulo = new Retangulo(entidade.x, entidade.y - offsetVidaY, entidade.width, 5, 0, 1, 0);
    }

    public boolean bateuParede(Estados estados) {
        for (Parede parede : estados.paredes) {
            if (entidade.overlaps(parede.getEntidade())) {
                return true;
            }
        }
        return false;
    }

    public boolean bateuVilao(Estados estados) {
        for (Vilao vilao : estados.viloes) {
            if (entidade.overlaps(vilao.getEntidade()) && vilao.isEstaVivo()) {
                setVida(vida - DANO);
            }
        }
        return false;
    }

    @Override
    public void gira(Estados estados) {
        double catetoAdjacente = (Gdx.input.getX() - posicao.x) ;
        double catetoOposto = (Gdx.graphics.getHeight() - Gdx.input.getY() - posicao.y);
        double myAtan = Math.atan(catetoOposto / catetoAdjacente) * 180 / Math.PI;
        alpha = (float) (180 + (Gdx.input.getX() < posicao.x? 270 + myAtan: 90 + myAtan));
    }

    @Override
    public void move(Estados estados) {
        gira(estados);
        double antigoX = entidade.x;
        double antigoY = entidade.y;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            entidade.x -= VELOCIDADE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            entidade.x += VELOCIDADE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            entidade.y += VELOCIDADE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            entidade.y -= VELOCIDADE;
        }
        if (bateuParede(estados)) {
            entidade.x = (float) antigoX;
            entidade.y = (float) antigoY;
        }
        posicao.x = entidade.x;
        posicao.y = entidade.y;
    }

    public void desenhaVida() {
        vidaRetangulo.setY(entidade.y - offsetVidaY);
        vidaRetangulo.setX(entidade.x);
        vidaRetangulo.setWidth((entidade.width * vida) / MAXVIDA);
        vidaRetangulo.render();
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public boolean getEstaVivo() {
        return estaVivo;
    }

    @Override
    public void render(Estados estados) {
        if (vida <= 0) {
            setEstaVivo(false);
        }
        bateuVilao(estados);
        desenhaVida();
        move(estados);
        super.render(estados);
    }
}
