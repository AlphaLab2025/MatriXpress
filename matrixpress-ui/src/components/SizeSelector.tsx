import React from 'react';

interface SizeSelectorProps {
  size: number;
  onSizeChange: (newSize: number) => void;
}

const SizeSelector: React.FC<SizeSelectorProps> = ({ size, onSizeChange }) => {
  return (
    <div className="size-selector">
      <label htmlFor="matrix-size">Tamanho da Matriz (N x N):</label>
      <input
        type="number"
        id="matrix-size"
        value={size}
        onChange={(e) => onSizeChange(Math.max(2, parseInt(e.target.value) || 2))}
        min="2"
        max="10"
      />
    </div>
  );
};

export default SizeSelector;