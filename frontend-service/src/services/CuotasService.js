import axios from 'axios';

const CUOTAS_API_URL = "http://localhost:8083/cuotas/";
class CuotasService {
    generarCuotas(rut) {
        return axios.post(`${CUOTAS_API_URL}generar/${rut}`);
    }
    


    getCuotas(rut){
        return axios.get(`${CUOTAS_API_URL}estudiantes/${rut}`);
    }
}

export default new CuotasService();

