package com.example.MatriXpress.models;

import com.example.MatriXpress.enumeration.TipoSolucao;

import java.util.Locale;

public class ResolvedorGaussiano {
    private static final double EPSILON = 1e-10;

    public Solucao resolver(SistemaLinear sistema){
        int n = sistema.getNumVariaveis();
        double[][] matriz = sistema.getMatrizAumentada();

        imprimirMatriz("Matriz Original:", matriz);

        for (int p = 0; p < n; p++){

            int max = p;
            for (int i = p + 1; i < n; i++){
                if (Math.abs(matriz[i][p]) > Math.abs(matriz[max][p])){
                    max = i;
                }
            }
            double[] temp = matriz[p];
            matriz[p] = matriz[max];
            matriz[max] = temp;

            if (Math.abs(matriz[p][p]) <= EPSILON){
                if (Math.abs(matriz[p][n]) <= EPSILON){
                    return new Solucao(TipoSolucao.INFINITAS_SOLUCOES, null);
                } else{
                    return new Solucao(TipoSolucao.SEM_SOLUCAO, null);
                }
            }

            for (int i = p + 1; i < n; i++){
                double fator = matriz[i][p] / matriz[p][p];
                matriz[i][p] = 0.0;
                for (int j = p + 1; j <= n; j++){
                    matriz[i][j] -= fator * matriz[p][j];
                }
            }
        }
        imprimirMatriz("Matriz Escalonada (Triangular Superior):", matriz);

        double[] solucaoValores = new double[n];
        for (int i = n - 1; i >= 0; i--){
            double soma = 0.0;
            for (int j = i + 1; j < n; j++){
                soma += matriz[i][j] * solucaoValores[j];
            }
            solucaoValores[i] = (matriz[i][n] - soma) / matriz[i][i];
        }

        return new Solucao(TipoSolucao.SOLUCAO_UNICA, solucaoValores);
    }

    private void imprimirMatriz(String titulo, double[][] matriz) {
        System.out.println(titulo);
        for (double[] linha : matriz) {
            for (double elemento : linha) {
                System.out.printf(Locale.forLanguageTag("pt-BR"), "%.2f\t", elemento);
            }
            System.out.println();
        }
        System.out.println();
    }
}
