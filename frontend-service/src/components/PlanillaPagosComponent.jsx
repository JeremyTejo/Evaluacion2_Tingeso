import React, { useState } from 'react';
import NotasService from '../services/NotasService';
import HeaderComponent from './Headers/HeaderComponent';

const PlanillaPagosComponent = () => {
  const [rut, setRut] = useState('');
  const [planilla, setPlanilla] = useState(null);
  const [error, setError] = useState('');

  const handleObtenerPlanilla = async () => {
    if (!rut) {
      setError('Por favor, ingresa un RUT válido.');
      return;
    }

    try {
      const planillaObtenida = await NotasService.obtenerPlanillaPorRut(rut);
      setPlanilla(planillaObtenida);
      setError('');
    } catch (error) {
      console.error('Error al obtener la planilla de pagos', error);
      setError('Error al obtener la planilla de pagos. Asegúrate de que el RUT sea correcto.');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <HeaderComponent />
      <h2>Planilla de Pagos</h2>
      <div style={{ marginBottom: '10px' }}>
        <input 
          type="text" 
          value={rut} 
          onChange={(e) => setRut(e.target.value)} 
          placeholder="Ingrese RUT del Estudiante" 
          style={{ marginRight: '10px' }}
        />
        <button onClick={handleObtenerPlanilla}>Obtener Planilla</button>
      </div>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {planilla && (
        <div style={{ backgroundColor: '#f0f0f0', padding: '10px', borderRadius: '5px' }}>
          <p><strong>Monto Total Arancel:</strong> {planilla.montoTotalArancel}</p>
          <p><strong>Descuento Aplicado:</strong> {planilla.descuentoAplicado}%</p>
          <p><strong>Intereses:</strong> {planilla.intereses}</p>
          <p><strong>Monto Total Pago:</strong> {planilla.montoTotalPago}</p>
        </div>
      )}
    </div>
  );
};

export default PlanillaPagosComponent;
