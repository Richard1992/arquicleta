package aes.arquicleta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aes.arquicleta.logic.CalificacionService;
import aes.arquicleta.model.Calificacion;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    /**
     * Obtener todas las calificaciones.
     * @return lista de calificaciones
     */
    @GetMapping
    public List<Calificacion> getAllCalificaciones() {
        return calificacionService.getAllCalificaciones();
    }

    /**
     * Guardar una nueva calificación.
     * @param calificacion la calificación a guardar
     * @return la calificación guardada
     */
    @PostMapping
    public Calificacion createCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionService.saveCalificacion(calificacion);
    }

    /**
     * Obtener una calificación por su ID.
     * @param id el ID de la calificación
     * @return la calificación encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Integer id) {
        Calificacion calificacion = calificacionService.getCalificacionById(id);
        if (calificacion != null) {
            return ResponseEntity.ok(calificacion);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    /**
     * Actualizar una calificación existente.
     * @param id el ID de la calificación a actualizar
     * @param calificacionDetails los nuevos detalles de la calificación
     * @return la calificación actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(
            @PathVariable Integer id,
            @RequestBody Calificacion calificacionDetails) {
        
        Calificacion updatedCalificacion = calificacionService.updateCalificacion(id, calificacionDetails);
        if (updatedCalificacion != null) {
            return ResponseEntity.ok(updatedCalificacion);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    /**
     * Eliminar una calificación por su ID.
     * @param id el ID de la calificación a eliminar
     * @return respuesta de la eliminación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Integer id) {
        boolean isDeleted = calificacionService.deleteCalificacion(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 si se eliminó
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encontró
    }
}
