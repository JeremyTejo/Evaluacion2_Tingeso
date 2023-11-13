import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import HeaderComponent from "./Headers/HeaderComponent";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Swal from 'sweetalert2';
import EstudianteService from "../services/EstudianteService";

function AgregarEstudianteComponent(props){

    const initialState = {
        rut: "",
        nombre: "",
        apellido: "",
        fechaNacimiento: "",
        tipoColegio: "",
        nombreColegio: "",
        fechaEgresoColegio: "",
    };

    const [input, setInput] = useState(initialState);
    const navigate = useNavigate();
    const navigateHome = () => {
        navigate("/");
    };
    
    const changeRutHandler = event => {
        setInput({ ...input, rut: event.target.value });
    };
    const changeNombresHandler = event => {
        setInput({ ...input, nombre: event.target.value });
    };
    const changeApellidoHandler = event => {
        setInput({ ...input, apellido: event.target.value });
    };
    const changeFechaNacimientoHandler = event => {
        setInput({ ...input, fechaNacimiento: event.target.value });
    };
    const changefechaEgresoColegioIDHandler = event => {
        setInput({ ...input, fechaEgresoColegio: event.target.value });
    };
    const changeTipoColegioHandler = event => {
        setInput({ ...input, tipoColegio: event.target.value });
    };
    const changeNombreColegioHandler = event => {
        setInput({ ...input, nombreColegio: event.target.value });
    };

    
    const ingresarEstudiante = (event) => {
        Swal.fire({
            title: "¿Desea registrar el estudiante?",
            text: "No podra cambiarse en caso de equivocación",
            icon: "question",
            showDenyButton: true,
            confirmButtonText: "Confirmar",
            confirmButtonColor: "rgb(68, 194, 68)",
            denyButtonText: "Cancelar",
            denyButtonColor: "rgb(190, 54, 54)",
        }).then((result) => {
            if (result.isConfirmed) {
                console.log(input.title);
                let newEstudiante = {
                    rut: input.rut,
                    nombre: input.nombre,
                    apellido: input.apellido,
                    fechaNacimiento: input.fechaNacimiento,
                    tipoColegio: input.tipoColegio,
                    nombreColegio: input.nombreColegio,
                    fechaEgresoColegio: input.fechaEgresoColegio,
                    fechaIngresoColegio: "",
                };
                console.log(newEstudiante);
                EstudianteService.createEstudiante(newEstudiante);
                Swal.fire({
                    title: "Enviado",
                    timer: 2000,
                    icon: "success",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                      },
                    })
                navigateHome();
            }
        });
    };

    return(
        <div className="general">
            <HeaderComponent />
            <div className="container-create">
                <Form>
                    <Form.Group className="mb-3" controlId="rut" value = {input.rut} onChange={changeRutHandler}>
                        <Form.Label className="agregar">Rut:</Form.Label>
                        <Form.Control className="agregar" type="text" name="rut"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombre" value = {input.nombre} onChange={changeNombresHandler}>
                        <Form.Label className="agregar">Nombres:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombre"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="apellido" value = {input.apellido} onChange={changeApellidoHandler}>
                        <Form.Label className="agregar">Apellidos:</Form.Label>
                        <Form.Control className="agregar" type="text" name="apellido"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="fechaNacimiento" value = {input.fechaNacimiento} onChange={changeFechaNacimientoHandler}>
                        <Form.Label className="agregar">Fecha de Nacimiento:</Form.Label>
                        <Form.Control className="agregar" type="date" name="fechaNacimiento"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="fechaEgresoColegio" value = {input.fechaEgresoColegio} onChange={changefechaEgresoColegioIDHandler}>
                        <Form.Label className="agregar">Año de egreso del colegio:</Form.Label>
                        <Form.Control className="agregar" type="date" name="fechaEgresoColegio"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="tipoColegio">
                        <Form.Label className="agregar"> Tipo: </Form.Label>
                        <select className="agregar" name="tipoColegio" required value = {input.tipoColegio} onChange={changeTipoColegioHandler}>
                            <option value="tipoColegio" disabled>Tipo colegio</option>
                            <option value="Municipal">Municipal</option>
                            <option value="Subvencionado">Subvencionado</option>
                            <option value="Privado">Privado</option>
                        </select>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombreColegio" value = {input.nombreColegio} onChange={changeNombreColegioHandler}>
                        <Form.Label className="agregar">Nombre del colegio:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombreColegio"/>
                    </Form.Group>
                    <Button className="boton" onClick={ingresarEstudiante}>Registrar Proveedor</Button>
                </Form>
            </div>
        </div>
    )
}
    export default AgregarEstudianteComponent;