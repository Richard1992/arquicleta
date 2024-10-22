package aes.arquicleta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aes.arquicleta.logic.CiudadService;
import aes.arquicleta.model.Ciudad;

@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {

	@Autowired
	private CiudadService ciudadService;

	// Obtener todas las ciudades por departamento
    @GetMapping("/{idDepartamento}")
    public ResponseEntity<List<Ciudad>> findCiudadByDepartamento(@PathVariable Integer idDepartamento) {
        List<Ciudad> ciudades = ciudadService.getCiudadesByIdDepartamento(idDepartamento);
        if (ciudades != null && !ciudades.isEmpty()) {
            return ResponseEntity.ok(ciudades); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
