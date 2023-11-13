import React, { useState } from 'react';
import axios from 'axios';
import NotasService from '../services/NotasService';


const CargaNotasComponent = () => {
    // Estilos
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
        }
    };

    // ... (tu lógica del componente)

    return (
        <div style={estilos.contenedor}>
            <h2 style={estilos.titulo}>Cargar Notas</h2>
            <input type="file" /* ...otros atributos... */ />
            <button style={estilos.boton}>Subir Archivo</button>
        </div>
    );
};

export default CargaNotasComponent;
