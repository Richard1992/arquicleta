package aes.arquicleta.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.Ciudad;
import aes.arquicleta.model.CiudadRepository;

@Service
public class CiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;

	// Método para obtener ciudades por departamento
    public List<Ciudad> getCiudadesByIdDepartamento(Integer idDepartamento) {
        return ciudadRepository.findByDepartamentoId(idDepartamento);
    }
}
