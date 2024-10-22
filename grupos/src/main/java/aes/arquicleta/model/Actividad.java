package aes.arquicleta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actividades") // Nombre de la tabla en la base de datos
@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con parámetros para todos los campos
@Builder // Permite construir objetos de forma más legible
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es un campo autoincremental
	private Integer id;

	@Column(nullable = false, length = 100) // Especifica restricciones en la columna
	private String nombre; // Nombre de la actividad

	@Column(columnDefinition = "TEXT") // Define que es un texto largo
	private String descripcion; // Descripción breve de la actividad

	private Integer duracion; // Duración típica en minutos

	@Enumerated(EnumType.STRING) // Almacena el valor del enum como una cadena
	private Intensidad intensidad; // Nivel de intensidad

	@Column(name = "equipamiento_requerido", length = 255) // Nombre de la columna en la DB
	private String equipamientoRequerido; // Equipamiento necesario

	@Column(columnDefinition = "TEXT") // Define que es un texto largo
	private String descripcionDetallada; // Descripción amplia de la actividad

	@Column(name = "fecha_creacion", updatable = false) // No se actualizará
	private LocalDateTime fechaCreacion; // Fecha de creación

	@Enumerated(EnumType.STRING) // Almacena el valor del enum como una cadena
	private EstadoActivo activo; // Estado de la actividad

	// Inicializa la fecha de creación al crear la entidad
	@PrePersist
	protected void onCreate() {
		fechaCreacion = LocalDateTime.now();
	}

	// Enum para representar los niveles de intensidad
	public enum Intensidad {
		Baja, Media, Alta
	}

	// Enum para representar el estado activo/inactivo
	public enum EstadoActivo {
		Activo, Inactivo
	}
}
