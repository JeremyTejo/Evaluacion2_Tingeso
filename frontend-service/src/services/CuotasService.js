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
}

export default new CuotasService();

