package aes.arquicleta.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aes.arquicleta.logic.ActividadService;
import aes.arquicleta.model.Actividad;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/actividades") // Mapeo de la URL base
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class ActividadController {

    private final ActividadService actividadService; // Inyección del servicio

    // Crear una nueva actividad
    @PostMapping
    public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad actividad) {
        Actividad nuevaActividad = actividadService.crearActividad(actividad);
        return new ResponseEntity<>(nuevaActividad, HttpStatus.CREATED); // Retorna 201 Created
    }

    // Obtener todas las actividades
    @GetMapping
    public ResponseEntity<List<Actividad>> obtenerTodasLasActividades() {
        List<Actividad> actividades = actividadService.obtenerTodasLasActividades();
        return new ResponseEntity<>(actividades, HttpStatus.OK); // Retorna 200 OK
    }

    // Obtener una actividad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Actividad> obtenerActividadPorId(@PathVariable Integer id) {
        return actividadService.obtenerActividadPorId(id)
                .map(actividad -> new ResponseEntity<>(actividad, HttpStatus.OK)) // Retorna 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retorna 404 Not Found
    }

    // Actualizar una actividad
    @PutMapping("/{id}")
    public ResponseEntity<Actividad> actualizarActividad(@PathVariable Integer id, @RequestBody Actividad actividadActualizada) {
        try {
            Actividad actividad = actividadService.actualizarActividad(id, actividadActualizada);
            return new ResponseEntity<>(actividad, HttpStatus.OK); // Retorna 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
        }
    }
}
