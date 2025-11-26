import { useState, useEffect } from 'react';
import { solveLinearSystem } from './services/apiService';
import type { SolucaoResponse } from './types/api';
import SizeSelector from './components/SizeSelector';
import MatrixGrid from './components/MatrixGrid';
import ResultDisplay from './components/ResultDisplay';
import './App.css'; 

function App() {
  
  const [size, setSize] = useState<number>(3);
  
  const [matrix, setMatrix] = useState<string[][]>([]);
  
  const [resultData, setResultData] = useState<SolucaoResponse | null>(null);
  
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);


  useEffect(() => {
    const newMatrix = Array.from({ length: size }, () =>
      Array.from({ length: size + 1 }, () => '') 
    );
    setMatrix(newMatrix);
    setResultData(null); 
    setError(null);      
  }, [size]);


  const handleInputChange = (value: string, row: number, col: number) => {
    const newMatrix = matrix.map((r, i) =>
      i === row ? r.map((c, j) => (j === col ? value : c)) : r
    );
    setMatrix(newMatrix);
  };

  const handleSubmit = async () => {
    setIsLoading(true);
    setResultData(null);
    setError(null);

    const coeficientes = matrix.map(row =>
      row.slice(0, size).map(val => parseFloat(val) || 0)
    );
    const constantes = matrix.map(row => parseFloat(row[size]) || 0);

    try {
      const data = await solveLinearSystem({ coeficientes, constantes });
      
      setResultData(data);
    } catch (err) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError('Ocorreu um erro desconhecido ao conectar com o servidor.');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container">
      <h1 className="header-title">MatriXpress</h1>
      <p className="header-subtitle">Resolvedor de Sistemas de Equações Lineares</p>

      <SizeSelector size={size} onSizeChange={setSize} />
      
      <div className="matrix-container">
        <MatrixGrid 
          size={size} 
          matrix={matrix} 
          onInputChange={handleInputChange} 
        />
      </div>
      
      <button 
        onClick={handleSubmit} 
        disabled={isLoading} 
        className="solve-button"
      >
        {isLoading ? 'Calculando...' : 'Resolver Sistema'}
      </button>

      <ResultDisplay 
        resultData={resultData} 
        error={error} 
        isLoading={isLoading} 
      />
    </div>
  );
}

export default App;