package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Estados.Estados;


public class Entidade implements MetodosEntidade {
    protected Rectangle entidade;
    protected Texture img;
    protected TextureRegion region;
    protected Sound som;
    protected Vector2 posicao;
    protected String nomeImagem;
    protected float alpha;
    protected int VELOCIDADE;

    public Rectangle getEntidade() {
        return entidade;
    }

    public void setEntidade(Rectangle entidade) {
        this.entidade = entidade;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public void setSom(Sound som) {
        this.som = som;
    }

    public void setPosicao(Vector2 posicao) {
        this.posicao = posicao;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setVELOCIDADE(int VELOCIDADE) {
        this.VELOCIDADE = VELOCIDADE;
    }

    public Texture getImg() {
        return img;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public Sound getSom() {
        return som;
    }

    public Vector2 getPosicao() {
        return posicao;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public float getAlpha() {
        return alpha;
    }

    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public Entidade(int x, int y, int width, int height, String nomeImagem) {
        create(x, y, width, height, nomeImagem);
    }

    public void create(int x, int y, int width, int height, String nomeImagem) {
        entidade = new Rectangle();
        posicao = new Vector2(x, y);
        entidade.x = posicao.x;
        entidade.y = posicao.y;
        entidade.setWidth(width);
        entidade.setHeight(height);
        this.nomeImagem = nomeImagem;
        alpha = 0;
        VELOCIDADE = 0;
        img = new Texture(Gdx.files.internal(this.nomeImagem));
        region = new TextureRegion(img, img.getWidth(), img.getHeight());
    }

    @Override
    public void render(Estados estados) {
        estados.getBatch().begin();
        estados.getBatch().draw(region, posicao.x, posicao.y, entidade.getWidth() / 2, entidade.getHeight() / 2, entidade.getWidth(), entidade.getHeight(), 1, 1, alpha);
        estados.getBatch().end();
    }

    @Override
    public void move(Estados estados) {

    }

    @Override
    public void gira(Estados estados) {

    }

    @Override
    public void morre(Estados estados) {

    }
}
