import React, { useState, useEffect } from "react";
import CuotasService from '../services/CuotasService';
import HeaderComponent from './Headers/HeaderComponent';

function ListaCuotasComponent() {
    const [cuotas, setCuotas] = useState([]);
    const [rut, setRut] = useState('');

    useEffect(() => {
        if (rut) {
            CuotasService.listarCuotasEstudiante(rut).then((res) => {
                console.log("Response data Cuotas:", res.data);
                setCuotas(res.data);
            });
        }
    }, [rut]);

    return (
        <div className="general">
            <HeaderComponent />
            <div align="center" className="container-2">
                <h1><b>Listado de Cuotas</b></h1>
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
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default ListaCuotasComponent;
