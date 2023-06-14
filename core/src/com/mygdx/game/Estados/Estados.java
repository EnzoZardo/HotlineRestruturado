package com.mygdx.game.Estados;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.mygdx.game.Arquivos.Arquivos;
import com.mygdx.game.Entidades.*;
import com.mygdx.game.Ferramentas.Retangulo;
import com.mygdx.game.Gerenciador;
import com.mygdx.game.Lib.Matematica;

import java.util.ArrayList;

public class Estados {
    public Heroi heroi;
    public Arma arma;
    public SpriteBatch batch;
    public DelayedRemovalArray<Vilao> viloes;
    public DelayedRemovalArray<Parede> paredes;
    public DelayedRemovalArray<Projetil> projeteis;
    public DelayedRemovalArray<Retangulo> retangulos;
    public BitmapFont font;
    public Arquivos arquivos;
    public ArrayList<String[]> arr;

    public Estados(String caminhoFase) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        viloes = new DelayedRemovalArray<>();
        paredes = new DelayedRemovalArray<>();
        projeteis = new DelayedRemovalArray<>();
        retangulos = new DelayedRemovalArray<>();
        arquivos = new Arquivos();
        arr = arquivos.ler(caminhoFase, "");
    }

    public void populaPlanta() {
        arma = new Arma(100, 100, 7, 25);
        for (int y = 0; y < arr.size(); y++) {
            int tamanhoY = (Gdx.graphics.getHeight() / arr.size()) + 1;
            for (int x = 0; x < arr.get(y).length; x++) {
                int tamanhoX = (Gdx.graphics.getWidth() / (arr.get(y).length - 1)) + 1;
                switch (arr.get(y)[x]) {
                    case("P"):
                        paredes.add(new Parede(x * tamanhoX, y * tamanhoY, tamanhoX,  tamanhoY));
                        break;
                    case("V"):
                        viloes.add(new Vilao(x * tamanhoX, y * tamanhoY, 50, 25));
                        break;
                    case("H"):
                        heroi = new Heroi(x * tamanhoX, y * tamanhoY, 50, 25);
                        break;
                    case("T"):
                        retangulos.add(new Retangulo(x * tamanhoX, y * tamanhoY, tamanhoX,  tamanhoY, 0f, 0f, 0f));
                        break;
                    default:
                        continue;
                }
            }
        }
    }

    public void renderRetangulos() {
        for (Retangulo retangulo : retangulos) {
            retangulo.render();
        }
    }

    public void renderViloes() {
        for (Vilao vilao: viloes) {
            vilao.render(this);
        }
    }

    public void disposeViloes() {
        for (Vilao vilao: viloes) {
            vilao.getImg().dispose();
        }
    }

    public void renderParedes() {
        for (Parede parede: paredes) {
            parede.render(this);
        }
    }

    public void disposeParedes() {
        for (Parede parede: paredes) {
            parede.getImg().dispose();
        }
    }

    public void renderProjeteis() {
        for (Projetil projetil: projeteis) {
            projetil.render(this);
        }
    }

    public void disposeProjeteis() {
        for (Projetil projetil: projeteis) {
            projetil.getImg().dispose();
        }
    }

    public void renderPontos() {
        batch.begin();
        font.getData().setScale(2);
        font.draw(batch,"Pontos: " + (Gerenciador.Pontos), 850, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Entidade getHeroi() {
        return heroi;
    }

    public Entidade getArma() {
        return arma;
    }
}
