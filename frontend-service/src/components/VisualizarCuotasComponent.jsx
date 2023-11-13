import React, { useState, useEffect } from "react";
import CuotasService from '../services/CuotasService';
import HeaderComponent from './Headers/HeaderComponent';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';

function VisualizarCuotasComponent() {
    const [cuotas, setCuotas] = useState([]);
    const [rut, setRut] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        cargarCuotas();
    }, [rut]);

    const pagarCuota = (idCuota) => {
        CuotasService.pagarCuota(idCuota)
            .then((response) => {
                cargarCuotas();
                Swal.fire({
                    title: 'Pago Realizado',
                    text: 'El pago de la cuota ha sido registrado exitosamente.',
                    icon: 'success',
                    confirmButtonText: 'Volver al Listado'
                }).then((result) => {
                    if (result.isConfirmed) {
                        navigate('/visualizar_cuotas');
                    }
                });
            })
            .catch((error) => {
                console.error("Error al pagar la cuota:", error);
            });
    };

    const cargarCuotas = () => {
        if (rut) {
            CuotasService.obtenerCuotasPorEstudiante(rut).then((res) => {
                setCuotas(res.data);
            });
        }
    };

    return (
        <div className="general">
            <HeaderComponent />
            <div align="center" className="container-2">
                <h1><b>Visualizar Cuotas</b></h1>
                <input
                    type="text"
                    value={rut}
                    onChange={(e) => setRut(e.target.value)}
                    placeholder="Ingrese RUT del estudiante"
                />
                <table border="1" className="content-table">
                    <thead>
                        <tr>
                            <th>ID Cuota</th>
                            <th>RUT Estudiante</th>
                            <th>Fecha de Vencimiento</th>
                            <th>Monto</th>
                            <th>Estado</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        {cuotas.map((cuota) => (
                            <tr key={cuota.idCuota}>
                                <td>{cuota.idCuota}</td>
                                <td>{cuota.rutEstudiante}</td>
                                <td>{cuota.fechaVencimiento}</td>
                                <td>{cuota.montoCuota}</td>
                                <td>{cuota.estadoCuota}</td>
                                <td>
                                    {cuota.estadoCuota === "Pendiente" && (
                                        <button onClick={() => pagarCuota(cuota.idCuota)}
                                        style={{backgroundColor: 'blue', color: 'white', padding: '5px 10px', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                                            Pagar
                                        </button>
                                    )}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default VisualizarCuotasComponent;
