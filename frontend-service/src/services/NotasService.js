import axios from 'axios';

const NOTAS_API_URL = 'http://localhost:8084/notas/';

class NotasService {
  importarNotas(archivo) {
    const formData = new FormData();
    formData.append('archivo', archivo);
    return axios.post(`${NOTAS_API_URL}importar`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
  }

  obtenerNotasPorEstudiante(rut) {
    return axios.get(`${NOTAS_API_URL}estudiantes/${rut}`)
                .then(response => response.data)
                .catch(error => {
                    console.error('Error al obtener las notas', error);
                    throw error;
                });
  }

  obtenerPlanillaPorRut(rut) {
    return axios.get(`${NOTAS_API_URL}planilla/${rut}`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error al obtener la planilla', error);
        throw error;
      });
  }

  // Aquí puedes agregar otros métodos relacionados con las notas si es necesario
}

export default new NotasService();