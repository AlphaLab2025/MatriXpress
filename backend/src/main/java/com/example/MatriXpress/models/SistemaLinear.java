package com.example.MatriXpress.models;

public class SistemaLinear {

    private final double [][] coeficientes;
    private final double [] constantes;
    private final int numVariaveis;

    public SistemaLinear(double[][] coeficientes, double[] constantes) {
        if (coeficientes == null || constantes == null || coeficientes.length != constantes.length) {
            throw new IllegalArgumentException("Dados do sistema inválidos.");
        }
        this.coeficientes = coeficientes;
        this.constantes = constantes;
        this.numVariaveis = constantes.length;
    }

    public int getNumVariaveis() {
        return numVariaveis;
    }

    public double[][] getMatrizAumentada() {
        double[][] aumentada = new double[numVariaveis][numVariaveis + 1];
        for (int i = 0; i < numVariaveis; i++) {
            for (int j = 0; j < numVariaveis; j++) {
                aumentada[i][j] = this.coeficientes[i][j];
            }
            aumentada[i][numVariaveis] = this.constantes[i];
        }
        return aumentada;
    }
}
