package aes.arquicleta.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aes.arquicleta.model.Grupo;
import aes.arquicleta.model.GrupoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Inyección de dependencias a través del constructor
public class GrupoService {

    private final GrupoRepository grupoRepository; // Inyección del repositorio

    // Crear un nuevo grupo
    @Transactional
    public Grupo crearGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    // Obtener todos los grupos
    public List<Grupo> obtenerTodosLosGrupos() {
        return grupoRepository.findAll();
    }

    // Obtener un grupo por ID
    public Optional<Grupo> obtenerGrupoPorId(Integer id) {
        return grupoRepository.findById(id);
    }

    // Actualizar un grupo
    @Transactional
    public Grupo actualizarGrupo(Integer id, Grupo grupoActualizado) {
        // Comprobar si el grupo existe
        if (!grupoRepository.existsById(id)) {
            throw new RuntimeException("Grupo no encontrado con ID: " + id);
        }
        grupoActualizado.setId(id); // Establecer el ID del grupo a actualizar
        return grupoRepository.save(grupoActualizado);
    }
}
