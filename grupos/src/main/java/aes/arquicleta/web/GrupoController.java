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

import aes.arquicleta.logic.GrupoService;
import aes.arquicleta.model.Grupo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grupos") // Mapeo de la URL base
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class GrupoController {

	private final GrupoService grupoService; // Inyección del servicio

	// Crear un nuevo grupo
	@PostMapping
	public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo) {
		Grupo nuevoGrupo = grupoService.crearGrupo(grupo);
		return new ResponseEntity<>(nuevoGrupo, HttpStatus.CREATED); // Retorna 201 Created
	}

	// Obtener todos los grupos
	@GetMapping
	public ResponseEntity<List<Grupo>> obtenerTodosLosGrupos() {
		List<Grupo> grupos = grupoService.obtenerTodosLosGrupos();
		return new ResponseEntity<>(grupos, HttpStatus.OK); // Retorna 200 OK
	}

	// Obtener un grupo por ID
	@GetMapping("/{id}")
	public ResponseEntity<Grupo> obtenerGrupoPorId(@PathVariable Integer id) {
		return grupoService.obtenerGrupoPorId(id).map(grupo -> new ResponseEntity<>(grupo, HttpStatus.OK)) // Retorna
																											// 200 OK
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retorna 404 Not Found
	}

	// Actualizar un grupo
	@PutMapping("/{id}")
	public ResponseEntity<Grupo> actualizarGrupo(@PathVariable Integer id, @RequestBody Grupo grupoActualizado) {
		try {
			Grupo grupo = grupoService.actualizarGrupo(id, grupoActualizado);
			return new ResponseEntity<>(grupo, HttpStatus.OK); // Retorna 200 OK
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
		}
	}
}
