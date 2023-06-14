package com.mygdx.game.Lib;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Matematica {
    private static Random random = new Random();

    public static Vector2 vec2Diff(Vector2 vector20, Vector2 vector21) {
        return (new Vector2((vector20.x) - (vector21.x), (vector20.y) - (vector21.y)));
    }

    public static double pitagoras(Vector2 catetos) {
        return Math.sqrt(Math.pow(catetos.x, 2) + Math.pow(catetos.y, 2));
    }

    public static boolean vectorCollision(Vector2 p1, Vector2 p2, Vector2 n1, Vector2 n2) {
        float uA = ((n2.x-n1.x)*(p1.y-n1.y) - (n2.y-n1.y)*(p1.x-n1.x)) / ((n2.y-n1.y)*(p2.x-p1.x) - (n2.x-n1.x)*(p2.y-p1.y));
        float uB = ((p2.x-p1.x)*(p1.y-n1.y) - (p2.y-p1.y)*(p1.x-n1.x)) / ((n2.y-n1.y)*(p2.x-p1.x) - (n2.x-n1.x)*(p2.y-p1.y));
        return (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1);
    }

    public static int randInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}


