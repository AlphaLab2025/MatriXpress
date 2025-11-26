package com.example.MatriXpress.models;

import java.util.List;

import com.example.MatriXpress.enumeration.TipoSolucao;

public class Solucao {

    private final TipoSolucao tipoSolucao;
    private final double[] valores;
    private final List<String> passos; 

    public Solucao(TipoSolucao tipoSolucao, double[] valores, List<String> passos) {
        this.tipoSolucao = tipoSolucao;
        this.valores = valores;
        this.passos = passos; 
    }

    public TipoSolucao getTipoSolucao() {
        return tipoSolucao;
    }

    public double[] getValores() {
        return valores;
    }

    public List<String> getPassos() {
        return passos; 
    }

  @Override
    public String toString() {
        switch (tipoSolucao) {
            case SOLUCAO_UNICA:
                StringBuilder sb = new StringBuilder();
                sb.append("Conjunto Solução:\n");
                sb.append("S = { (");
                
                for (int i = 0; i < valores.length; i++) {
                    sb.append(decimalParaFracao(valores[i]));
                    if (i < valores.length - 1) {
                        sb.append(", ");
                    }
                }
                
                sb.append(") }");
                return sb.toString();

            case INFINITAS_SOLUCOES:
  
                return "Conjunto Solução:\n" +
                       "S = { v \u2208 \u211D\u207F | O sistema é possível e indeterminado (SPI) }";

            case SEM_SOLUCAO:
  
                return "Conjunto Solução:\n" +
                       "S = \u2205 (Conjunto Vazio - Sistema Impossível)";

            default:
                return "Estado da solução desconhecido.";
        }
    }

    private String decimalParaFracao(double numero) {

        if (Math.abs(numero) < 1e-10) return "0";

        if (Math.abs(numero - Math.round(numero)) < 1e-10) {
            return String.format("%.0f", numero);
        }

 
        double sinal = Math.signum(numero);
        double x = Math.abs(numero);

 
        double tolerancia = 1.0E-6;
        double h1 = 1; double h2 = 0;
        double k1 = 0; double k2 = 1;
        double b = x;
        
      
        int maxIteracoes = 25; 
        int i = 0;

        do {
            long a = (long) Math.floor(b);
            double aux = h1;
            h1 = a * h1 + h2;
            h2 = aux;

            aux = k1;
            k1 = a * k1 + k2;
            k2 = aux;


            double resto = b - a;
            if (Math.abs(resto) < 1e-10) break; 
            
            b = 1 / resto;
            i++;
        } while (Math.abs(x - h1 / k1) > x * tolerancia && i < maxIteracoes);


        return String.format("%.0f/%.0f", h1 * sinal, k1);
    }
}