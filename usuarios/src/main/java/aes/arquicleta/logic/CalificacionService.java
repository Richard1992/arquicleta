package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.Calificacion;
import aes.arquicleta.model.CalificacionRepository;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    /**
     * Obtener todas las calificaciones.
     * @return lista de calificaciones
     */
    public List<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    /**
     * Guardar una nueva calificación.
     * @param calificacion la calificación a guardar
     * @return la calificación guardada
     */
    public Calificacion saveCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    /**
     * Obtener una calificación por su ID.
     * @param id el ID de la calificación
     * @return la calificación encontrada
     */
    public Calificacion getCalificacionById(Integer id) {
        Optional<Calificacion> optionalCalificacion = calificacionRepository.findById(id);
        return optionalCalificacion.orElse(null); // Retorna null si no se encuentra
    }

    /**
     * Actualizar una calificación existente.
     * @param id el ID de la calificación a actualizar
     * @param calificacionDetails los nuevos detalles de la calificación
     * @return la calificación actualizada
     */
    public Calificacion updateCalificacion(Integer id, Calificacion calificacionDetails) {
        Calificacion calificacion = getCalificacionById(id);

        if (calificacion != null) {
            calificacion.setCiclista(calificacionDetails.getCiclista());
            calificacion.setEntrenador(calificacionDetails.getEntrenador());
            calificacion.setCalificacion(calificacionDetails.getCalificacion());
            calificacion.setComentario(calificacionDetails.getComentario());
            return calificacionRepository.save(calificacion);
        }
        
        return null; // Retorna null si no se encuentra la calificación
    }

    /**
     * Eliminar una calificación por su ID.
     * @param id el ID de la calificación a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean deleteCalificacion(Integer id) {
        if (calificacionRepository.existsById(id)) {
            calificacionRepository.deleteById(id);
            return true; // Retorna true si se eliminó
        }
        return false; // Retorna false si no se encontró la calificación
    }
}
