import axios from 'axios';
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import HeaderComponent from "./Headers/HeaderComponent";
import { Form, Button } from "react-bootstrap";

const CUOTAS_API_BASE_URL = "http://localhost:8081/cuotas"; // Asegúrate que el puerto y la ruta coincidan con tu backend

const GenerarCuotasComponent = () => {
    const [rut, setRut] = useState("");
    const [cuotas, setCuotas] = useState("");
    const navigate = useNavigate();

    const navigateList = () => {
        navigate("/lista_cuotas");
    };

    const generarCuotas = async (rut, numeroCuotas) => {
        try {
            const response = await axios.post(`${CUOTAS_API_BASE_URL}/generar/${rut}`, {
                // Aquí puedes enviar datos adicionales si tu API lo requiere
            });
            return response;
        } catch (error) {
            throw error;
        }
    };

    const ingresarCuotas = async () => {
        if (!rut) {
            Swal.fire("Ingrese un RUT", "", "warning");
            return;
        }
        if (!cuotas) {
            Swal.fire("Seleccione un número de cuotas", "", "error");
            return;
        }
        try {
            const response = await generarCuotas(rut, cuotas);
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
                            <Form.Label className="agregar">Rut:</Form.Label>
                            <Form.Control 
                                className="agregar" 
                                type="text" 
                                name="rut"
                                value={rut}
                                onChange={(e) => setRut(e.target.value)}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="Cuotas">
                            <Form.Label className="Cuotas"> Cuotas: </Form.Label>
                            <Form.Select 
                                id="Cuotas"
                                name="Cuotas"
                                required
                                value={cuotas}
                                onChange={(e) => setCuotas(e.target.value)}
                            >
                                <option value="" disabled>
                                    Seleccione las cuotas
                                </option>
                                {[...Array(10).keys()].map((number) => (
                                    <option key={number} value={number + 1}>
                                        {number + 1}
                                    </option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Button id="Generar" className="Generar" onClick={ingresarCuotas}>
                            Generar
                        </Button>
                    </Form>
                </div>
            </div>
        </div>
    );
};

export default GenerarCuotasComponent;