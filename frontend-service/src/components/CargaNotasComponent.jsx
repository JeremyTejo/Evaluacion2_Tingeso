import React, { useState } from 'react';
import NotasService from '../services/NotasService';
import HeaderComponent from './Headers/HeaderComponent'

const CargaNotasComponent = () => {
    const [archivoSeleccionado, setArchivoSeleccionado] = useState(null);
    const [notasImportadas, setNotasImportadas] = useState([]);

    const estilos = {
        contenedor: {
            margin: 'auto',
            padding: '20px',
            borderRadius: '10px',
            background: '#f9f9f9',
            boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)',
            maxWidth: '600px',
        },
        titulo: {
            textAlign: 'center',
            color: '#333',
            marginBottom: '20px',
        },
        boton: {
            display: 'block',
            width: '100%',
            padding: '10px',
            marginTop: '10px',
            border: 'none',
            borderRadius: '5px',
            backgroundColor: '#4CAF50',
            color: 'white',
            cursor: 'pointer',
        },
        lista: {
            listStyleType: 'none',
            padding: 0,
        },
        itemLista: {
            backgroundColor: '#e0e0e0',
            margin: '5px 0',
            padding: '10px',
            borderRadius: '5px',
        }
    };

    const manejarCambioArchivo = (evento) => {
        setArchivoSeleccionado(evento.target.files[0]);
    };

    const manejarEnvioArchivo = () => {
        if (archivoSeleccionado) {
            NotasService.importarNotas(archivoSeleccionado)
                .then(response => {
                    setNotasImportadas(response.data);
                })
                .catch(error => {
                    console.error("Error al subir el archivo:", error);
                });
        }
    };

    return (
        <div style={estilos.contenedor}>
            <HeaderComponent/>
            <h2 style={estilos.titulo}>Cargar Notas</h2>
            <input type="file" onChange={manejarCambioArchivo} />
            <button style={estilos.boton} onClick={manejarEnvioArchivo}>Subir Archivo</button>

            {notasImportadas.length > 0 && (
                <div>
                    <h3 style={estilos.titulo}>Notas Importadas</h3>
                    <ul style={estilos.lista}>
                        {notasImportadas.map((nota, index) => (
                            <li key={index} style={estilos.itemLista}>
                                RUT: {nota.rutEstudiante}, Fecha: {nota.fechaExamen}, Puntuación: {nota.puntaje}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default CargaNotasComponent;
