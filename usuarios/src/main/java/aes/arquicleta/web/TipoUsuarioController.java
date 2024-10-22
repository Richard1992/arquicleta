package aes.arquicleta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aes.arquicleta.logic.TipoUsuarioService;
import aes.arquicleta.model.TipoUsuario;

@RestController
@RequestMapping("/api/tipo-usuario")
public class TipoUsuarioController {

	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	/**
	 * Obtener todos los tipos de usuarios.
	 * 
	 * @return lista de tipos de usuarios
	 */
	@GetMapping
	public List<TipoUsuario> getAllTiposUsuarios() {
		return tipoUsuarioService.getAllTiposUsuarios();
	}

	/**
	 * Obtener un tipo de usuario por su ID.
	 * 
	 * @param id el ID del tipo de usuario
	 * @return el tipo de usuario encontrado
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TipoUsuario> getTipoUsuarioById(@PathVariable Integer id) {
		TipoUsuario tipoUsuario = tipoUsuarioService.getTipoUsuarioById(id);
		if (tipoUsuario != null) {
			return ResponseEntity.ok(tipoUsuario);
		}
		return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
	}
}
