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

import aes.arquicleta.logic.InscripcionService;
import aes.arquicleta.model.Inscripcion;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inscripciones") // Mapeo de la URL base
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class InscripcionController {

	private final InscripcionService inscripcionService; // Inyección del servicio

	// Crear una nueva inscripción
	@PostMapping
	public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
		Inscripcion nuevaInscripcion = inscripcionService.crearInscripcion(inscripcion);
		return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED); // Retorna 201 Created
	}

	// Obtener todas las inscripciones
	@GetMapping
	public ResponseEntity<List<Inscripcion>> obtenerTodasLasInscripciones() {
		List<Inscripcion> inscripciones = inscripcionService.obtenerTodasLasInscripciones();
		return new ResponseEntity<>(inscripciones, HttpStatus.OK); // Retorna 200 OK
	}

	// Obtener una inscripción por ID
	@GetMapping("/{id}")
	public ResponseEntity<Inscripcion> obtenerInscripcionPorId(@PathVariable Integer id) {
		return inscripcionService.obtenerInscripcionPorId(id)
				.map(inscripcion -> new ResponseEntity<>(inscripcion, HttpStatus.OK)) // Retorna 200 OK
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retorna 404 Not Found
	}

	// Actualizar una inscripción
	@PutMapping("/{id}")
	public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable Integer id,
			@RequestBody Inscripcion inscripcionActualizada) {
		try {
			Inscripcion inscripcion = inscripcionService.actualizarInscripcion(id, inscripcionActualizada);
			return new ResponseEntity<>(inscripcion, HttpStatus.OK); // Retorna 200 OK
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
		}
	}
}
