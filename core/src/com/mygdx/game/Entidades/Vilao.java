package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Estados.Estados;
import com.mygdx.game.Ferramentas.Retangulo;
import com.mygdx.game.Lib.Matematica;

public class Vilao extends Entidade {
    private static final String NOMEIMAGEM = "vilao.png";
    private boolean estaVivo;
    private int vida = 20;
    private Retangulo vidaRetangulo;
    private final int MAXVIDA = 20, offsetVidaY = 15;

    public Vilao(int x, int y, int width, int height) {
        super(x, y, width, height, NOMEIMAGEM);
        setVELOCIDADE(Matematica.randInt(20, 25));
        estaVivo = true;
        vidaRetangulo = new Retangulo(entidade.x, entidade.y - offsetVidaY, entidade.width, 5, 1, 0, 0);
    }

    public void setVilaoDimensions(float width, float height) {
        entidade.width = width;
        entidade.height = height;
    }

    public void desenhaVida() {
        vidaRetangulo.setY(entidade.y - offsetVidaY);
        vidaRetangulo.setX(entidade.x);
        vidaRetangulo.setWidth(entidade.width * vida / MAXVIDA);
        vidaRetangulo.render();
    }


    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public boolean bateuParede(Estados estados) {
        for (Parede parede : estados.paredes) {
            if (entidade.overlaps(parede.getEntidade())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void gira(Estados estados) {
        double catetoAdjacente = (estados.heroi.getEntidade().x - posicao.x) ;
        double catetoOposto = (estados.heroi.getEntidade().x - posicao.y);
        double myAtan = Math.atan(catetoOposto / catetoAdjacente) * 180 / Math.PI;
        alpha = 180 + (float)(Gdx.input.getX() < posicao.x? 270 + myAtan: 90 + myAtan);
    }

    @Override
    public void move(Estados estados) {
        Vector2 toMove = Matematica.vec2Diff(estados.heroi.posicao, posicao);
        float antigoX = entidade.x;
        float antigoY = entidade.y;
        double x = Matematica.randInt(-1, 1) + (toMove.x / Matematica.pitagoras(posicao)) * this.VELOCIDADE;
        double y = Matematica.randInt(-1, 1) + (toMove.y / Matematica.pitagoras(posicao)) * this.VELOCIDADE;
        entidade.x += x;
        entidade.y += y;
        if (bateuParede(estados)) {
            entidade.x = antigoX;
            entidade.y = antigoY;
        }
        posicao.x = entidade.x;
        posicao.y = entidade.y;
    }

    private boolean enxergaHeroi(Estados estados) {
        for (int i = 0; i < estados.paredes.size; i++) {
            Parede bloco = estados.paredes.get(i);
            Vector2 diagonalRefp1 = estados.heroi.getPosicao();
            Vector2 diagonalRefp2 = posicao;
            Vector2 diagonal1p1 = bloco.getDiagonais().get(0)[0][0];
            Vector2 diagonal1p2 = bloco.getDiagonais().get(0)[0][1];
            Vector2 diagonal2p1 = bloco.getDiagonais().get(0)[1][0];
            Vector2 diagonal2p2 = bloco.getDiagonais().get(0)[1][1];
            if (Matematica.vectorCollision(diagonalRefp1, diagonalRefp2, diagonal1p1, diagonal1p2) || Matematica.vectorCollision(diagonalRefp1, diagonalRefp2, diagonal2p1, diagonal2p2)) {
                return true;
            }
        }
        return false;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    @Override
    public void render(Estados estados) {
        if (!enxergaHeroi(estados) && estaVivo) {
            move(estados);
            gira(estados);
        }
        desenhaVida();
        estados.batch.begin();
        if (estaVivo) estados.batch.draw(region, posicao.x, posicao.y, entidade.width/2, entidade.height/2, entidade.width, entidade.height, 1, 1, alpha);
        else estados.batch.draw(img, posicao.x, posicao.y, entidade.width, entidade.height);
        estados.batch.end();
    }
}
