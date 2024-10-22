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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inscripciones") // Nombre de la tabla en la base de datos
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con parámetros para todos los campos
@Builder // Permite construir objetos de forma más legible
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es un campo autoincremental
	private Integer id;

	@Column(name = "id_usuario") // Nombre de la columna en la DB
	private Integer idUsuario; // ID del usuario que se inscribe (ciclista o entrenador)

	@ManyToOne // Relación con el grupo
	@JoinColumn(name = "id_grupo", referencedColumnName = "id") // Columna de referencia
	private Grupo grupo; // Grupo en el que se inscribe

	@ManyToOne // Relación con la actividad
	@JoinColumn(name = "id_actividad", referencedColumnName = "id") // Columna de referencia
	private Actividad actividad; // Actividad en la que participa

	@Column(name = "fecha_inscripcion", updatable = false) // No se actualizará
	private LocalDateTime fechaInscripcion; // Fecha de inscripción

	@Column(name = "fecha_actividad") // Nombre de la columna en la DB
	private LocalDate fechaActividad; // Fecha de la actividad

	@Enumerated(EnumType.STRING) // Almacena el valor del enum como una cadena
	private EstadoInscripcion estadoInscripcion; // Estado de la inscripción

	@Column(columnDefinition = "TEXT") // Define que es un texto largo
	private String notas; // Notas adicionales sobre la inscripción

	@Column(name = "fecha_cancelacion") // Nombre de la columna en la DB
	private LocalDateTime fechaCancelacion; // Fecha en que se canceló la inscripción (si aplica)

	@Column(name = "id_entrenador") // Nombre de la columna en la DB
	private Integer idEntrenador; // ID del entrenador asignado

	// Inicializa la fecha de inscripción al crear la entidad
	@PrePersist
	protected void onCreate() {
		fechaInscripcion = LocalDateTime.now();
	}

	// Enum para representar los estados de inscripción
	public enum EstadoInscripcion {
		Confirmada, Pendiente, Cancelada
	}
}
