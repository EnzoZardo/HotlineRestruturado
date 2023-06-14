package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Telas.TelaFase1;
import com.mygdx.game.Telas.TelaMenu;

public class Gerenciador extends Game {
	public static int Pontos = 0;
	public static String Nome = "";
	@Override
	public void create() {
		setScreen(new TelaMenu(this));
	}

	public static void resetaGerenciador() {
		Pontos = 0;
		Nome = "";
	}

}
