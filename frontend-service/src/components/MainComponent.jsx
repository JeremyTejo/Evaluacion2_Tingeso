import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/main.css";
import "../styles/listas.css";
import "../styles/subir_excel.css";
import "../styles/navbar.css";
import "../styles/agregar.css";
import Estudiantes from "../images/estudiantes.png"
import ListaCuotas from "../images/plan-de-estudios.png"
import Cuota from "../images/grafico-circular.png"
import HeaderComponent from "./Headers/HeaderComponent";

function MainComponents() {
    const navigate = useNavigate();
    const handleClickGenerarCuotas = () => {
        navigate("/generar_cuotas");
    };
    const handleClickListaCuotas = () => {
        navigate("/lista_cuotas");
    };
    // Navega al componente que lista los estudiantes
    const handleClickListaEstudiantes = () => {
        navigate("/lista_estudiantes");
    };
    
    // Navega al componente que permite agregar un nuevo estudiante
    const handleClickAgregarEstudiante = () => {
        navigate("/agregar_estudiante");
    };
    return (
        <div>
            <HeaderComponent></HeaderComponent>
            <div className="container_main">
                <div className="card" onClick={handleClickListaEstudiantes}>
                    <img id="lista_proveedores" src={Estudiantes} alt="Imagen_1" />
                    <h2>Listado de Estudiantes</h2>
                </div>
                <div className="card" onClick={handleClickAgregarEstudiante}>
                    {/* Asegúrate de tener una imagen adecuada para 'Agregar Estudiante' */}
                    <img id="agregar_estudiante" src={Estudiantes} alt="Agregar Estudiante" />
                    <h2>Agregar Estudiante</h2>
                </div>
                <div className="card" onClick={handleClickGenerarCuotas}>
                    <img id="generar_cuotas" src={Cuota} alt="Imagen_2" />
                    <h2>Generar Cuotas</h2>
                </div>
                <div className="card" onClick={handleClickListaCuotas}>
                    <img id="lista_cuotas" src={ListaCuotas} alt="Imagen_3" />
                    <h2>Listado de Cuotas</h2>
                </div>
            </div>
        </div>
    );
}

export default MainComponents;
