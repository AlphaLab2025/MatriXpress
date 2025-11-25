export interface SistemaRequest {
  coeficientes: number[][];
  constantes: number[];
}

export interface SolucaoResponse {
  tipo: 'SOLUCAO_UNICA' | 'INFINITAS_SOLUCOES' | 'SEM_SOLUCAO';
  valores: number[] | null;
  mensagem: string;
}