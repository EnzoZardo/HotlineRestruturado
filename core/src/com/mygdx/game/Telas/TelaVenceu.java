package com.mygdx.game.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arquivos.Arquivos;
import com.mygdx.game.Ferramentas.Botao;
import com.mygdx.game.Gerenciador;

import java.io.IOException;
import java.util.*;

public class TelaVenceu extends InputAdapter implements Screen {
    Texture fundo;
    SpriteBatch batch;
    Botao botaoVoltar;
    Gerenciador gerenciador;

    public TelaVenceu(Gerenciador gerenciador) {
        fundo = new Texture(Gdx.files.internal("FundoVenceu.png"));
        batch = new SpriteBatch();
        botaoVoltar = new Botao((Gdx.graphics.getWidth() - 288) / 2, (Gdx.graphics.getHeight() - 230) / 2, 263, 93, 214/255f, 68/255f, 179/255f, "Voltar");
        this.gerenciador = gerenciador;
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        try {
            HashMap<String, String> pontos = new HashMap<>();
            HashMap<String, String> sortedMap = new HashMap<>();
            ArrayList<String> list = new ArrayList<>();
            (new Arquivos()).escrever("core/src/com/mygdx/game/Dados/dados.txt", Gerenciador.Nome + " - " + Gerenciador.Pontos + System.lineSeparator(), true);
            for (String[] arr : (new Arquivos()).ler("core/src/com/mygdx/game/Dados/dados.txt", " - ")) {
                pontos.put(arr[0], arr[1]);
            }

            for (Map.Entry<String, String> entry : pontos.entrySet()) {
                list.add(entry.getValue());
            }
            Collections.sort(list, new Comparator<String>() {
                public int compare(String str, String str1) {
                    return (str).compareTo(str1);
                }
            });
            for (String str : list) {
                for (Map.Entry<String, String> entry : pontos.entrySet()) {
                    if (entry.getValue().equals(str)) {
                        sortedMap.put(entry.getKey(), str);
                    }
                }
            }
            if (sortedMap.size() > 5) {
                while (sortedMap.size() > 5) {
                    sortedMap.remove(sortedMap.keySet().toArray()[0]);
                }
            }
            System.out.println(sortedMap);
            (new Arquivos()).escrever("core/src/com/mygdx/game/Dados/dados.txt", sortedMap.toString().replace("=", " - ").replace(", ", System.lineSeparator()).replace("{", "").replace("}", "") + System.lineSeparator(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        botaoVoltar.update();
        if (botaoVoltar.clicou() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Gerenciador.resetaGerenciador();
            gerenciador.setScreen(new TelaMenu(gerenciador));
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
