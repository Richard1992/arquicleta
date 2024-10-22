package aes.arquicleta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aes.arquicleta.logic.DepartamentoService;
import aes.arquicleta.model.Departamento;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // Obtener todas los departamentos por pais
    @GetMapping("/{idPais}")
    public ResponseEntity<List<Departamento>> findDepartamentoByPais(@PathVariable Integer idPais) {
        List<Departamento> departamentos = departamentoService.getDepartamentoByIdPais(idPais);
        if (departamentos != null && !departamentos.isEmpty()) {
            return ResponseEntity.ok(departamentos); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
