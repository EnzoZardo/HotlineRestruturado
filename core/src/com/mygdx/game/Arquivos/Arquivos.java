package com.mygdx.game.Arquivos;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Arquivos {

    public ArrayList<String[]> ler(String nomeArquivo, String separador) {
        File arquivo = new File(nomeArquivo);
        ArrayList<String[]> arr = new ArrayList<>();
        try {
            try (FileReader fr = new FileReader(arquivo);
                 BufferedReader bf = new BufferedReader(fr)) {
                String linha_lida;
                do {
                    linha_lida = bf.readLine();
                    if (linha_lida != null) {
                        arr.add(linha_lida.split(separador));
                    }
                } while (linha_lida != null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void escrever(String nomeArquivo, String pEscrever, boolean append) throws IOException {
        File arquivo = new File(nomeArquivo);
        try {
            try (FileWriter fw = new FileWriter(arquivo, append);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)
            ) {
                pw.print(pEscrever);
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo");
        }
    }

}
