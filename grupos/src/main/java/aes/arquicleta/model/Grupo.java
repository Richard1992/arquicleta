package aes.arquicleta.model;

import java.time.LocalDate;
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
@Table(name = "grupos") // Nombre de la tabla en la base de datos
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con parámetros para todos los campos
@Builder // Permite construir objetos de forma más legible
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es un campo autoincremental
	private Integer id;

	@Column(nullable = false, length = 100) // Especifica restricciones en la columna
	private String nombre; // Nombre del grupo

	@Column(name = "cantidad_maxima", nullable = false) // Nombre de la columna en la DB
	private Integer cantidadMaxima; // Cantidad máxima de participantes

	@Column(columnDefinition = "TEXT") // Define que es un texto largo
	private String descripcion; // Descripción del grupo

	@Column(name = "tipo_grupo", length = 50) // Nombre de la columna en la DB
	private String tipoGrupo; // Tipo de grupo (ej. competitivo, recreativo)

	@Enumerated(EnumType.STRING) // Almacena el valor del enum como una cadena
	private Nivel nivel; // Nivel requerido para unirse

	private String frecuencia; // Frecuencia de actividades (ej. semanal, quincenal)

	private String horario; // Horario de actividades (ej. sábados a las 10:00 AM)

	private String lugar; // Ubicación principal

	@Column(name = "id_creador") // Nombre de la columna en la DB
	private Integer idCreador; // ID del usuario que creó el grupo

	@Column(name = "fecha_inicio") // Nombre de la columna en la DB
	private LocalDate fechaInicio; // Fecha de inicio de actividades

	@Enumerated(EnumType.STRING) // Almacena el valor del enum como una cadena
	private EstadoActivo activo; // Estado del grupo

	@Column(name = "fecha_creacion", updatable = false) // No se actualizará
	private LocalDateTime fechaCreacion; // Fecha de creación del grupo

	// Inicializa la fecha de creación al crear la entidad
	@PrePersist
	protected void onCreate() {
		fechaCreacion = LocalDateTime.now();
	}

	// Enum para representar los niveles
	public enum Nivel {
		Principiante, Intermedio, Avanzado
	}

	// Enum para representar el estado activo/inactivo
	public enum EstadoActivo {
		Activo, Inactivo, Cerrado
	}
}
