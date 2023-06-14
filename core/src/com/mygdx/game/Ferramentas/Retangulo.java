package com.mygdx.game.Ferramentas;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Retangulo {
    private ShapeRenderer shapeRenderer;
    private float x, y, width, height, r, g, b;

    public Retangulo(float x, float y, float width, float height, float r, float g, float b) {
        shapeRenderer = new ShapeRenderer();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(r, g, b, 1);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}
