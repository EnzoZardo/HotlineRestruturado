package com.mygdx.game.Entidades;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class Parede extends Entidade {
    private static final String NOMEIMAGEM = "parede.png";

    public Parede(int x, int y, int width, int height) {
        super(x, y, width, height, NOMEIMAGEM);
    }

    public ArrayList<Vector2[][]> getDiagonais() {
        ArrayList<Vector2[][]> diagonais = new ArrayList<>();
        Vector2[][] diagonal = new Vector2[][] {
                { new Vector2(posicao.x, posicao.y), new Vector2(posicao.x + entidade.width, entidade.y + entidade.height) },
                { new Vector2(posicao.x, posicao.y + entidade.height), new Vector2(posicao.x + entidade.width, posicao.y) }
        };
        diagonais.add(diagonal);
        return diagonais;
    }
}
