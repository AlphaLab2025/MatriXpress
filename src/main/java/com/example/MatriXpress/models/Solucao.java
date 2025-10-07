package com.example.MatriXpress.models;

import com.example.MatriXpress.enumeration.TipoSolucao;

public class Solucao {

    private final TipoSolucao tipoSolucao;
    private final double[] valores;

    public Solucao(TipoSolucao tipoSolucao, double[] valores) {
        this.tipoSolucao = tipoSolucao;
        this.valores = valores;
    }

    public TipoSolucao getTipoSolucao() {
        return tipoSolucao;
    }

    public double[] getValores() {
        return valores;
    }

    @Override
    public String toString() {
        switch (tipoSolucao) {
            case SOLUCAO_UNICA:
                StringBuilder sb = new StringBuilder("Solução Única Encontrada:\n");
                for (int i = 0; i < valores.length; i++) {
                    sb.append(String.format("x%d = %.2f\n", i + 1 ,valores[i]));
                }
                return sb.toString();

            case INFINITAS_SOLUCOES:
                return "O sistema possui infinitas soluções.";

            case SEM_SOLUCAO:
                return "O sistema não possui solução.";

            default:
                return "Estado da solução desconhecido.";
        }
    }
}
