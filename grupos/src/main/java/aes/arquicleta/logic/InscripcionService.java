package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aes.arquicleta.model.Inscripcion;
import aes.arquicleta.model.InscripcionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class InscripcionService {

	private final InscripcionRepository inscripcionRepository; // Inyección del repositorio

	// Crear una nueva inscripción
	@Transactional
	public Inscripcion crearInscripcion(Inscripcion inscripcion) {
		return inscripcionRepository.save(inscripcion);
	}

	// Obtener todas las inscripciones
	public List<Inscripcion> obtenerTodasLasInscripciones() {
		return inscripcionRepository.findAll();
	}

	// Obtener una inscripción por ID
	public Optional<Inscripcion> obtenerInscripcionPorId(Integer id) {
		return inscripcionRepository.findById(id);
	}

	// Actualizar una inscripción
	@Transactional
	public Inscripcion actualizarInscripcion(Integer id, Inscripcion inscripcionActualizada) {
		// Comprobar si la inscripción existe
		if (!inscripcionRepository.existsById(id)) {
			throw new RuntimeException("Inscripción no encontrada con ID: " + id);
		}
		inscripcionActualizada.setId(id); // Establecer el ID de la inscripción a actualizar
		return inscripcionRepository.save(inscripcionActualizada);
	}
}
