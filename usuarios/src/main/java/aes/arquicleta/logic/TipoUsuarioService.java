package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.TipoUsuario;
import aes.arquicleta.model.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	/**
	 * Obtener todos los tipos de usuarios.
	 * 
	 * @return lista de tipos de usuarios
	 */
	public List<TipoUsuario> getAllTiposUsuarios() {
		return tipoUsuarioRepository.findAll();
	}

	/**
	 * Obtener un tipo de usuario por su ID.
	 * 
	 * @param id el ID del tipo de usuario
	 * @return el tipo de usuario encontrado o null si no existe
	 */
	public TipoUsuario getTipoUsuarioById(Integer id) {
		Optional<TipoUsuario> optionalTipoUsuario = tipoUsuarioRepository.findById(id);
		return optionalTipoUsuario.orElse(null); // Retorna null si no se encuentra
	}
}
