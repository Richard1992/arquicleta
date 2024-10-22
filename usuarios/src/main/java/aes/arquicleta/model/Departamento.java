package aes.arquicleta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departamento") // Nombre de la tabla en la base de datos
@Data // Genera getters y setters automáticamente
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para ID autoincremental
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 100) // Campo obligatorio, 100 caracteres
	private String nombre;

	@Column(name = "codigo", length = 10) // Campo opcional, máximo 10 caracteres
	private String codigo;

	@Column(name = "prefijo_telefonico", length = 10) // Campo opcional, máximo 10 caracteres
	private String prefijoTelefonico;

	@ManyToOne // Relación muchos a uno con País
	@JoinColumn(name = "id_pais") // Nombre de la columna en la tabla de departamento
	private Pais pais; // Referencia a la entidad Pais

	// Los métodos getter y setter se generan automáticamente por Lombok
}
