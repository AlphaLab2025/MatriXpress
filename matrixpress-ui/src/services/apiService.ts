import type { SistemaRequest, SolucaoResponse } from '../types/api';

const API_URL = 'http://localhost:8080/api/resolver';

export const solveLinearSystem = async (payload: SistemaRequest): Promise<SolucaoResponse> => {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    const errorData = await response.json().catch(() => null);
    throw new Error(errorData?.message || `Erro na requisição: ${response.statusText}`);
  }

  return response.json();
};