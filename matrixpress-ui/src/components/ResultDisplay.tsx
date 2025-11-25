import React, { useMemo } from 'react';

interface ResultDisplayProps {
  solution: string | null;
  error: string | null;
  isLoading: boolean;
}

const ResultDisplay: React.FC<ResultDisplayProps> = ({ solution, error, isLoading }) => {
  const formattedSolution = useMemo(() => {
    if (!solution) return null;
    const lines = solution.split('\n').filter(line => line.trim() !== '');
    if (lines.length === 0) return null;
    
    const title = lines[0];
    const values = lines.slice(1);

    return (
      <>
        <p className="result-message">{title}</p>
        <ul className="result-list">
          {values.map((val, index) => (
            <li key={index}>{val.trim()}</li>
          ))}
        </ul>
      </>
    );
  }, [solution]);

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

      {!solution && !isLoading && !error && (
        <p className="result-placeholder">
          Preencha a matriz e clique em "Resolver Sistema" para ver o resultado.
        </p>
      )}
    </div>
  );
};

export default ResultDisplay;