package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.Usuario;
import aes.arquicleta.model.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtener todos los usuarios.
     * @return lista de usuarios
     */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Guardar un nuevo usuario.
     * @param usuario el usuario a guardar
     * @return el usuario guardado
     */
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtener un usuario por su ID.
     * @param id el ID del usuario
     * @return el usuario encontrado o null si no existe
     */
    public Usuario getUsuarioById(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.orElse(null); // Retorna null si no se encuentra
    }

    /**
     * Actualizar un usuario existente.
     * @param id el ID del usuario a actualizar
     * @param usuarioDetails los nuevos detalles del usuario
     * @return el usuario actualizado o null si no existe
     */
    public Usuario updateUsuario(Integer id, Usuario usuarioDetails) {
        Usuario usuario = getUsuarioById(id); // Obtiene el usuario o null

        if (usuario != null) {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setContrasena(usuarioDetails.getContrasena());
            usuario.setDireccion(usuarioDetails.getDireccion());
            usuario.setEdad(usuarioDetails.getEdad());
            usuario.setGenero(usuarioDetails.getGenero());
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());
            usuario.setCiudad(usuarioDetails.getCiudad());
            return usuarioRepository.save(usuario);
        }
        
        return null; // Retorna null si no se encuentra el usuario
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id el ID del usuario a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true; // Retorna true si se eliminó
        }
        return false; // Retorna false si no se encontró el usuario
    }
}
