package aes.arquicleta.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
	// Método para encontrar ciudades por ID del departamento
    List<Ciudad> findByDepartamentoId(Integer idDepartamento);
}
