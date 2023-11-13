import axios from 'axios';

const CUOTAS_API_URL = "http://localhost:8083/cuotas/";

class CuotasService {
    generarCuotas(rut) {
        return axios.post(`${CUOTAS_API_URL}generar/${rut}`);
    }

    obtenerCuotasPorEstudiante(rut) {
        return axios.get(`${CUOTAS_API_URL}estudiantes/${rut}`);
    }

    pagarCuota(idCuota) {
        return axios.put(`${CUOTAS_API_URL}pagar/${idCuota}`);
    }

    // Método para obtener la planilla de pagos de un estudiante
    obtenerPlanillaPagos(rut) {
        return axios.get(`${CUOTAS_API_URL}planilla/${rut}`);
    }

    // Método para actualizar la planilla de pagos de un estudiante
    actualizarPlanillaPagos(rut) {
        return axios.put(`${CUOTAS_API_URL}actualizar/${rut}`);
    }
}

export default new CuotasService();
