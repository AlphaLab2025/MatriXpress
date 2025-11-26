import React, { useMemo } from 'react';
import type { SolucaoResponse } from '../types/api';

interface ResultDisplayProps {
  resultData: SolucaoResponse | null;
  error: string | null;
  isLoading: boolean;
}

const ResultDisplay: React.FC<ResultDisplayProps> = ({ resultData, error, isLoading }) => {
  
  const formattedSolution = useMemo(() => {
    if (!resultData || !resultData.mensagem) return null;
    const lines = resultData.mensagem.split('\n').filter(line => line.trim() !== '');
    if (lines.length === 0) return null;
    
    const title = lines[0];
    const values = lines.slice(1);

    return (
      <div className="mb-6">
        <p className="result-message">{title}</p>
        <ul className="result-list">
          {values.map((val, index) => <li key={index}>{val.trim()}</li>)}
        </ul>
      </div>
    );
  }, [resultData]);

  const renderStep = (passo: string, index: number) => {
    const [titulo, ...resto] = passo.split('\n');
    const matrizTexto = resto.join('\n'); // Junta o resto de volta

    return (
      <div key={index} className="step-card">
        <div className="step-header">
          Passo {index + 1}: {titulo.replace(':', '')}
        </div>
        <div className="step-content">
          {matrizTexto.trim()}
        </div>
      </div>
    );
  };

  return (
    <div className="result-container">
      <h2>Resultado</h2>
      
      {isLoading && <p>Calculando...</p>}
      
      {error && (
        <div className="result-error">
          <p className="result-error-title">Erro!</p>
          <p>{error}</p>
        </div>
      )}
      
      {formattedSolution && !isLoading && !error && formattedSolution}

      {resultData && resultData.passos && resultData.passos.length > 0 && (
        <div className="steps-section">
          <h3 className="steps-title">Detalhamento do Cálculo:</h3>
          <div>
            {resultData.passos.map((passo, index) => renderStep(passo, index))}
          </div>
        </div>
      )}

      {!resultData && !isLoading && !error && (
        <p className="result-placeholder">
          Preencha a matriz e clique em "Resolver Sistema" para ver o resultado.
        </p>
      )}
    </div>
  );
};

export default ResultDisplay;