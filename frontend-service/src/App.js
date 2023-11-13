import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainComponent from "./components/MainComponent";
import AgregarEstudianteComponent from "./components/AgregarEstudianteComponent";
import ListadoEstudianteComponent from "./components/ListadoEstudianteComponent";
import GenerarCuotasComponent from "./components/GenerarCuotasComponent";
import VisualizarCuotasComponent from './components/VisualizarCuotasComponent'; // Ajusta la ruta según la ubicación de tu componente
import CargaNotasComponent from './components/CargaNotasComponent';
import PlanillaPagosComponent from "./components/PlanillaPagosComponent";




function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<MainComponent />} />
                <Route path="/agregar_estudiante" element={<AgregarEstudianteComponent />} />
                <Route path="/lista_estudiantes" element={<ListadoEstudianteComponent />} />
                <Route path="/generar_cuotas" element={<GenerarCuotasComponent />} />
                <Route path="/visualizar_cuotas" element={<VisualizarCuotasComponent />} />
                <Route path="/cargar_notas" element={<CargaNotasComponent />} />
                <Route path="/planilla_pagos" element={<PlanillaPagosComponent/>}/>
            </Routes>
        </Router>
    );
}

export default App;
