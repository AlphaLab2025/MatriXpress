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
    
                    sb.append(String.format("x%d = %s\n", i + 1, decimalParaFracao(valores[i])));
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

    private String decimalParaFracao(double numero) {
        double tolerancia = 1.0E-6; 

        if (Math.abs(numero - Math.round(numero)) < tolerancia) {
            return String.format("%.0f", numero);
        }

        double h1 = 1; double h2 = 0;
        double k1 = 0; double k2 = 1;
        double b = numero;
        
   
        do {
            long a = (long) Math.floor(b);
            double aux = h1; 
            h1 = a * h1 + h2; 
            h2 = aux;
            
            aux = k1; 
            k1 = a * k1 + k2; 
            k2 = aux;
            
            b = 1 / (b - a);
        } while (Math.abs(numero - h1 / k1) > numero * tolerancia);


        if (k1 < 0) {
            h1 = -h1;
            k1 = -k1;
        }

        return String.format("%.0f/%.0f", h1, k1);
    }
}