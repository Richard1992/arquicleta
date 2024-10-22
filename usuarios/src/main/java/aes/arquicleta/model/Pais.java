package aes.arquicleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pais") // Nombre de la tabla en la base de datos
@Data // Genera getters y setters automáticamente
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para ID autoincremental
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 100) // Campo obligatorio, 100 caracteres
	private String nombre;

	@Column(name = "codigo_iso", nullable = false, unique = true, length = 3) // Código ISO único y no nulo
	private String codigoIso;

	@Column(name = "moneda", length = 50) // Campo opcional, máximo 50 caracteres
	private String moneda;

	@Column(name = "zona_horaria", length = 50) // Campo opcional, máximo 50 caracteres
	private String zonaHoraria;
}
