package com.mygdx.game.Entidades;

import com.mygdx.game.Estados.Estados;

public interface MetodosEntidade {
    void render(Estados estados);
    void move(Estados estados);
    void gira(Estados estados);
    void morre(Estados estados);
}
