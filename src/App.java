import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        
        // Matriz aumentada [A|b]
        double[][] matrizAumentada = {
            {1, 2, 3, 9},
            {2, -1, 1, 8},
            {3, 0, -1, 3}
        };

        // Chama o método para resolver o sistema
        resolverSistema(matrizAumentada);
    }

    public static void resolverSistema(double[][] matriz) {
        int n = matriz.length; 

        System.out.println("Matriz Original:");
        imprimirMatriz(matriz);

  
        for (int p = 0; p < n; p++) {
            
            // Encontra o pivô (maior elemento em valor absoluto na coluna atual)
            // para garantir estabilidade numérica e evitar divisão por zero.
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matriz[i][p]) > Math.abs(matriz[max][p])) {
                    max = i;
                }
            }

            // Troca a linha atual com a linha do pivô
            double[] temp = matriz[p];
            matriz[p] = matriz[max];
            matriz[max] = temp;

            // Se o pivô for zero, o sistema pode não ter solução única
            if (Math.abs(matriz[p][p]) <= 1e-10) {
                System.out.println("O sistema não tem solução única.");
                return;
            }

            // Zera os elementos abaixo do pivô na coluna p
            for (int i = p + 1; i < n; i++) {
                double fator = matriz[i][p] / matriz[p][p];
                // Subtrai o múltiplo da linha do pivô da linha atual
                for (int j = p; j < n + 1; j++) {
                    matriz[i][j] -= fator * matriz[p][j];
                }
            }
        }
        
        System.out.println("\nMatriz Escalonada (Triangular Superior):");
        imprimirMatriz(matriz);

        double[] solucao = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double soma = 0.0;
            // Calcula a soma dos termos conhecidos (a_ij * x_j)
            for (int j = i + 1; j < n; j++) {
                soma += matriz[i][j] * solucao[j];
            }
            // Isola a variável x_i
            solucao[i] = (matriz[i][n] - soma) / matriz[i][i];
        }
        
        System.out.println("\nSolução do sistema:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.2f\n", i + 1, solucao[i]);
        }
    }

    public static void imprimirMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%.2f\t", matriz[i][j]);
            }
            System.out.println();
        }
    }
}