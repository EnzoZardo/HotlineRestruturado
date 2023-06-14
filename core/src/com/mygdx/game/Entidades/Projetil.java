package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Estados.Estados;
import com.mygdx.game.Gerenciador;

public class Projetil extends Entidade {
    private static final String NOMEIMAGEM = "tiro.png";
    private final int DANO = 5;
    private boolean estaVivo = true;

    public Projetil(int x, int y, int width, int height, float alpha) {
        super(x, y, width, height, NOMEIMAGEM);
        super.alpha = alpha;
        super.VELOCIDADE = 15;
    }

    public void limites(Estados estados) {
        if ((posicao.x - VELOCIDADE < 0 || posicao.y - VELOCIDADE < 0 || posicao.x + VELOCIDADE  > Gdx.graphics.getWidth() || posicao.y + VELOCIDADE  > Gdx.graphics.getHeight())) {
            estados.projeteis.removeValue(this, true);
        }
    }

    public void matar(Estados estados) {
        for (int p = 0; p < estados.projeteis.size; p++) {
            for (int v = 0; v < estados.viloes.size; v++) {
                if (estados.projeteis.get(p).entidade.overlaps(estados.viloes.get(v).entidade) && estados.viloes.get(v).isEstaVivo()) {
                    estados.projeteis.get(p).posicao.x = -40;
                    estados.projeteis.get(p).posicao.y = -40;
                    estados.projeteis.get(p).entidade.setX(estados.projeteis.get(p).posicao.x);
                    estados.projeteis.get(p).entidade.setY(estados.projeteis.get(p).posicao.y);
                    estados.projeteis.get(p).setEstaVivo(false);
                    estados.viloes.get(v).setVida(estados.viloes.get(v).getVida() - DANO);
                    if (estados.viloes.get(v).getVida() <= 0) {
                        estados.viloes.get(v).setVida(0);
                        estados.viloes.get(v).setImg(new Texture("vilao_morto.png"));
                        estados.viloes.get(v).setVilaoDimensions(50f, 50f);
                        estados.viloes.get(v).setEstaVivo(false);
                        Gerenciador.Pontos += 10 * estados.arma.getTiros();
                    }
                    break;
                }
            }
        }
    }

    public void parede(Estados estados) {
        for (int p = 0; p < estados.projeteis.size; p++) {
            for (int b = 0; b < estados.paredes.size; b++) {
                if (estados.projeteis.get(p).entidade.overlaps(estados.paredes.get(b).entidade)) {
                    estados.projeteis.get(p).posicao.x = -40;
                    estados.projeteis.get(p).posicao.y = -40;
                    estados.projeteis.get(p).entidade.setX(estados.projeteis.get(p).posicao.x);
                    estados.projeteis.get(p).entidade.setY(estados.projeteis.get(p).posicao.y);
                    estados.projeteis.get(p).setEstaVivo(false);
                    break;
                }
            }
        }
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    @Override
    public void move(Estados estados) {
        double x = Math.cos((alpha-90) * Math.PI/180) * VELOCIDADE;
        double y = Math.sin((alpha-90) * Math.PI/180) * VELOCIDADE;
        posicao.x += x;
        posicao.y += y;
        entidade.x = posicao.x;
        entidade.y = posicao.y;
    }

    @Override
    public void render(Estados estados) {
        if (estaVivo) {
            move(estados);
            limites(estados);
            parede(estados);
            matar(estados);
        } else {
            estados.projeteis.removeValue(this, true);
        }
        estados.batch.begin();
        estados.batch.draw(img, posicao.x, posicao.y);
        estados.batch.end();
    }
}
