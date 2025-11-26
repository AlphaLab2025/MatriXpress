package com.example.MatriXpress.models;

import com.example.MatriXpress.enumeration.TipoSolucao;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResolvedorGaussiano {
    private static final double EPSILON = 1e-10;

    public Solucao resolver(SistemaLinear sistema) {
        int n = sistema.getNumVariaveis();
        double[][] matriz = sistema.getMatrizAumentada();

        List<String> passos = new ArrayList<>();
        passos.add(formatarMatriz("Matriz Inicial:", matriz));

        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matriz[i][p]) > Math.abs(matriz[max][p])) {
                    max = i;
                }
            }

            if (max != p) {
                double[] temp = matriz[p];
                matriz[p] = matriz[max];
                matriz[max] = temp;
                passos.add(formatarMatriz("Troca de linhas (L" + (p + 1) + " <-> L" + (max + 1) + "):", matriz));
            }

            if (Math.abs(matriz[p][p]) <= EPSILON) {
                if (Math.abs(matriz[p][n]) <= EPSILON) {
                    return new Solucao(TipoSolucao.INFINITAS_SOLUCOES, null, passos);
                } else {
                    return new Solucao(TipoSolucao.SEM_SOLUCAO, null, passos);
                }
            }

            boolean houveAlteracao = false;
            for (int i = p + 1; i < n; i++) {
                double fator = matriz[i][p] / matriz[p][p];
                if (Math.abs(fator) > EPSILON) { 
                    matriz[i][p] = 0.0;
                    for (int j = p + 1; j <= n; j++) {
                        matriz[i][j] -= fator * matriz[p][j];
                    }
                    houveAlteracao = true;
                }
            }
            if (houveAlteracao) {
                passos.add(formatarMatriz("Zerando coluna " + (p + 1) + " abaixo do pivô:", matriz));
            }
        }

        passos.add(formatarMatriz("Matriz Escalonada Final:", matriz));

        double[] solucaoValores = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double soma = 0.0;
            for (int j = i + 1; j < n; j++) {
                soma += matriz[i][j] * solucaoValores[j];
            }
            solucaoValores[i] = (matriz[i][n] - soma) / matriz[i][i];
        }

        return new Solucao(TipoSolucao.SOLUCAO_UNICA, solucaoValores, passos);
    }

    private String formatarMatriz(String titulo, double[][] matriz) {
        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append("\n");
        for (double[] linha : matriz) {
            sb.append("| ");
            for (int j = 0; j < linha.length; j++) {
                if (j == linha.length - 1)
                    sb.append("| ");
                sb.append(String.format(Locale.US, "%6.2f ", linha[j]));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
}