import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/main.css";
import "../styles/listas.css";
import "../styles/subir_excel.css";
import "../styles/navbar.css";
import "../styles/agregar.css";
import Estudiantes from "../images/estudiantes.png";
import ListaCuotas from "../images/plan-de-estudios.png"; // Asegúrate de que esta imagen sea la correcta
import Cuota from "../images/grafico-circular.png";
import HeaderComponent from "./Headers/HeaderComponent";

function MainComponent() {
    const navigate = useNavigate();

    const handleClickGenerarCuotas = () => {
        navigate("/generar_cuotas");
    };

    const handleClickListaEstudiantes = () => {
        navigate("/lista_estudiantes");
    };

    const handleClickAgregarEstudiante = () => {
        navigate("/agregar_estudiante");
    };

    const handleClickVisualizarCuotas = () => {
        navigate("/visualizar_cuotas");
    };
    const handleClickCargarNotas = () => {
        navigate("/cargar_notas");
    };    
    const handleClickPlanillaPagos = () => {
        navigate("/planilla_pagos");
    }
    return (
        <div>
            <HeaderComponent></HeaderComponent>
            <div className="container_main">
                <div className="card" onClick={handleClickListaEstudiantes}>
                    <img id="lista_proveedores" src={Estudiantes} alt="Imagen_1" />
                    <h2>Listado de Estudiantes</h2>
                </div>
                <div className="card" onClick={handleClickAgregarEstudiante}>
                    <img id="agregar_estudiante" src={Estudiantes} alt="Agregar Estudiante" />
                    <h2>Agregar Estudiante</h2>
                </div>
                <div className="card" onClick={handleClickGenerarCuotas}>
                    <img id="generar_cuotas" src={Cuota} alt="Imagen_2" />
                    <h2>Generar Cuotas</h2>
                </div>
                <div className="card" onClick={handleClickVisualizarCuotas}>
                    <img id="visualizar_cuotas" src={ListaCuotas} alt="Visualizar Cuotas" />
                    <h2>Visualizar Cuotas</h2>
                </div>
                <div className="card" onClick={handleClickCargarNotas}>
                    <img id="cargar_notas" src={ListaCuotas} alt="Cargar Notas" />
                    <h2>Cargar Notas</h2>
                </div>
                <div className="card" onClick={handleClickPlanillaPagos}>
                    <img id="planilla_pagos" src={ListaCuotas} alt="Planilla de Pagos " />
                    <h2>Planilla de Pagos</h2>
                </div>
            </div>
        </div>
    );
}

export default MainComponent;
