package com.mygdx.game.Telas;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Gerenciador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

//Fiz uma nova função para ler o arquivo.
//Pode mudar o que precisar.
// !!Mudar a referencia ao arquivo txt!!
//---- FileInputStream entradaArquivo = new FileInputStream
// (new File("C:/Users/2020306807/Desktop/HotlineRestruturado/ranking.txt"));


public class TelaGrafico extends JPanel {
    private double[] values;
    private String[] names;
    private String title;
    static String primeiroCompleto;
    static String segundoCompleto;
    static String terceiroCompleto;
    static String quartoCompleto;
    static String quintoCompleto;

    static String[] primeiroSeparado;
    static String[] segundoSeparado;
    static String[] terceiroSeparado;
    static String[] quartoSeparado;
    static String[] quintoSeparado;

    static String primeiroPontos;
    static String segundoPontos;
    static String terceiroPontos;
    static String quartoPontos;
    static String quintoPontos;

    BitmapFont font;
    private Color corNome;


    public TelaGrafico(double[] v, String[] n, String t) {
        names = n;
        values = v;
        title = t;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i])
                minValue = values[i];
            if (maxValue < values[i])
                maxValue = values[i];
        }

        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length;

        Font titleFont = new Font("Arial", Font.BOLD, 45);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0)
                valueY += (int) ((maxValue - values[i]) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            Color corTitle = new Color(128, 0, 128);

            g.setColor(corTitle);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(names[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(names[i], x, y);


        }
    }

    public static void LeTXT() throws FileNotFoundException{
        FileInputStream entradaArquivo = new FileInputStream("core/src/com/mygdx/game/Dados/dados.txt");
        Scanner lerArquivo = new Scanner(entradaArquivo);
        ArrayList<String> dados = new ArrayList<>();
        while (lerArquivo.hasNext()) {
            String linhatxt = lerArquivo.nextLine();
            if (linhatxt != null && !linhatxt.isEmpty()) {
                dados.add(linhatxt);
            }
        }

        primeiroCompleto = dados.get(0);
        segundoCompleto = dados.get(1);
        terceiroCompleto = dados.get(2);
        quartoCompleto = dados.get(3);
        quintoCompleto = dados.get(4);

        primeiroSeparado = primeiroCompleto.split("-");
        segundoSeparado = segundoCompleto.split("-");
        terceiroSeparado = terceiroCompleto.split("-");
        quartoSeparado = quartoCompleto.split("-");
        quintoSeparado = quintoCompleto.split("-");

        primeiroPontos = primeiroSeparado[1];
        segundoPontos = segundoSeparado[1];
        terceiroPontos = terceiroSeparado[1];
        quartoPontos = quartoSeparado[1];
        quintoPontos = quintoSeparado[1];

    }

    public TelaGrafico() throws FileNotFoundException {
        LeTXT();
        final JFrame f = new JFrame();
        f.setSize(1000, 600);
        f.setResizable(false);
        double[] values = new double[5];
        String[] names = new String[5];
        values[0] = Double.parseDouble(quintoPontos);
        names[0] = quintoCompleto;

        values[1] = Double.parseDouble(quartoPontos);
        names[1] = quartoCompleto;

        values[2] = Double.parseDouble(terceiroPontos);
        names[2] = terceiroCompleto;

        values[3] = Double.parseDouble(segundoPontos);
        names[3] = segundoCompleto;

        values[4] = Double.parseDouble(primeiroPontos);
        names[4] = primeiroCompleto;

        f.getContentPane().add(new TelaGrafico(values, names, "Ranking 5 Melhores"));

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.setVisible(false);
            }
        };
        f.addWindowListener(wndCloser);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

    }

    @Override
    public void show() {

    }
}


