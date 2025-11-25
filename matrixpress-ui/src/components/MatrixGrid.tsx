import React from 'react';

interface MatrixGridProps {
  size: number;
  matrix: string[][];
  onInputChange: (value: string, row: number, col: number) => void;
}

const MatrixGrid: React.FC<MatrixGridProps> = ({ size, matrix, onInputChange }) => {
  const headers = [' ', ...Array.from({ length: size }, (_, i) => `x${i + 1}`), 'b'];

  return (
    <div 
      className="matrix-grid" 
      style={{ gridTemplateColumns: `auto repeat(${size + 1}, 1fr)` }}
    >
      {headers.map((header, index) => (
        <div 
          key={`header-${index}`} 
          className={`matrix-header ${index === size + 1 ? 'constant-header' : ''}`}
        >
          {header}
        </div>
      ))}

      {matrix.map((row, i) => (
        <React.Fragment key={`row-${i}`}>
          <div className="matrix-row-label">
            Eq. {i + 1}
          </div>
          {row.map((cell, j) => (
            <input
              key={`${i}-${j}`}
              type="number"
              value={cell}
              onChange={(e) => onInputChange(e.target.value, i, j)}
              className={`matrix-input ${j === size ? 'constant-input' : ''}`}
              aria-label={`Input para linha ${i + 1}, coluna ${j === size ? 'constante' : `x${j + 1}`}`}
            />
          ))}
        </React.Fragment>
      ))}
    </div>
  );
};

export default MatrixGrid;