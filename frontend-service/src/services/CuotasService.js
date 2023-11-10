import axios from 'axios';

const CUOTAS_API_URL = "http://localhost:8081/cuotas/";

class CuotasService {

    generarCuotas(rut){
        return axios.post(CUOTAS_API_URL + "generar/" + rut);
    }
    getCuotas(rut){
        return axios.get(CUOTAS_API_URL + 'estudiante/' + rut);
    }

}
    
export default new CuotasService()