import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import HeaderComponent from "./Headers/HeaderComponent";
import { Form, Button } from "react-bootstrap";
import CuotasService from '../services/CuotasService';

const GenerarCuotasComponent = () => {
    const [rut, setRut] = useState('');
    const [numeroCuotas, setNumeroCuotas] = useState('');

    const navigate = useNavigate();

    const navigateList = () => {
        navigate("/lista_cuotas");
    };

    const generarCuotas = async (rut, numeroCuotas) => {
        try {
            const response = await CuotasService.generarCuotas(rut, numeroCuotas);
            return response;
        } catch (error) {
            throw error;
        }
    };

    const ingresarCuotas = async () => {
        if (!rut || !numeroCuotas) {
            Swal.fire("Faltan datos", "Por favor, ingrese un RUT y el número de cuotas", "warning");
            return;
        }

        try {
            const response = await generarCuotas(rut, numeroCuotas);
            if (response.status === 201) {
                Swal.fire("Cuotas Generadas", "Las cuotas han sido generadas correctamente", "success")
                    .then((result) => {
                        if (result.isConfirmed) {
                            navigateList();
                        }
                    });
            } else {
                throw new Error('Error al generar cuotas');
            }
        } catch (error) {
            let errorMessage = "No se pudieron generar las cuotas o no existe el usuario";
            if (error.response) {
                errorMessage = error.response.data.message || errorMessage;
            } else if (error.request) {
                errorMessage = "El servidor no está respondiendo";
            }
            Swal.fire("Error", errorMessage, "error");
        }
    };

    return (
        <div>
            <HeaderComponent />
            <div className="contenedor">
                <div className="Alinear">
                    <Form>
                        <Form.Group className="mb-3" controlId="rut">
                            <Form.Label>RUT:</Form.Label>
                            <Form.Control 
                                type="text" 
                                value={rut} 
                                onChange={(e) => setRut(e.target.value)} 
                            />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="numeroCuotas">
                            <Form.Label>Número de Cuotas:</Form.Label>
                            <Form.Control 
                                type="number" 
                                value={numeroCuotas} 
                                onChange={(e) => setNumeroCuotas(e.target.value)} 
                            />
                        </Form.Group>

                        <Button variant="primary" onClick={ingresarCuotas}>
                            Generar
                        </Button>
                    </Form>
                </div>
            </div>
        </div>
    );
};

export default GenerarCuotasComponent;

