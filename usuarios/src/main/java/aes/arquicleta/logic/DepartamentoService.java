package aes.arquicleta.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.Departamento;
import aes.arquicleta.model.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // Método para obtener departamento por pais
    public List<Departamento> getDepartamentoByIdPais(Integer idPais) {
        return departamentoRepository.findByPaisId(idPais);
    }
}

