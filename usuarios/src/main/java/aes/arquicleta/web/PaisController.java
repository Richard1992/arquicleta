package aes.arquicleta.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aes.arquicleta.logic.PaisService;
import aes.arquicleta.model.Pais;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pais")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    /**
     * Obtener todos los países.
     * @return lista de países
     */
    @GetMapping
    public List<Pais> findAll() {
        return paisService.getAllPaises();
    }
}
