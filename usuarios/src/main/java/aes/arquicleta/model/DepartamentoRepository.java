package aes.arquicleta.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	// Método para encontrar departamentos por ID del Pais
    List<Departamento> findByPaisId(Integer idPais);
}
