package aes.arquicleta.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aes.arquicleta.model.Pais;
import aes.arquicleta.model.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    /**
     * Obtener todos los países.
     * @return lista de países
     */
    public List<Pais> getAllPaises() {
        return paisRepository.findAll();
    }
}
