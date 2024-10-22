package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aes.arquicleta.model.Actividad;
import aes.arquicleta.model.ActividadRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class ActividadService {
	
	private final ActividadRepository actividadRepository; // Inyección del repositorio

	// Crear una nueva actividad
	@Transactional
	public Actividad crearActividad(Actividad actividad) {
		return actividadRepository.save(actividad);
	}

	// Obtener todas las actividades
	public List<Actividad> obtenerTodasLasActividades() {
		return actividadRepository.findAll();
	}

	// Obtener una actividad por ID
	public Optional<Actividad> obtenerActividadPorId(Integer id) {
		return actividadRepository.findById(id);
	}

	// Actualizar una actividad
	@Transactional
	public Actividad actualizarActividad(Integer id, Actividad actividadActualizada) {
		// Comprobar si la actividad existe
		if (!actividadRepository.existsById(id)) {
			throw new RuntimeException("Actividad no encontrada con ID: " + id);
		}
		actividadActualizada.setId(id); // Establecer el ID de la actividad a actualizar
		return actividadRepository.save(actividadActualizada);
	}
}
