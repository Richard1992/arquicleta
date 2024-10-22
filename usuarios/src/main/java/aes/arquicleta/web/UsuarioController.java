package aes.arquicleta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aes.arquicleta.logic.UsuarioService;
import aes.arquicleta.model.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    /**
     * Obtener todos los usuarios.
     * @return lista de usuarios
     */
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    /**
     * Guardar un nuevo usuario.
     * @param usuario el usuario a guardar
     * @return el usuario guardado
     */
    @PostMapping
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    /**
     * Obtener un usuario por su ID.
     * @param id el ID del usuario
     * @return el usuario encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    /**
     * Actualizar un usuario existente.
     * @param id el ID del usuario a actualizar
     * @param usuarioDetails los nuevos detalles del usuario
     * @return el usuario actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id el ID del usuario a eliminar
     * @return respuesta de la eliminación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        boolean isDeleted = usuarioService.deleteUsuario(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 si se eliminó
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encontró
    }
}
