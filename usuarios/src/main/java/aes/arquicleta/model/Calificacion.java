package aes.arquicleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "calificaciones")
@Data
public class Calificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ciclista", nullable = false)
	private Usuario ciclista; // Usuario que califica (ciclista)

	@ManyToOne
	@JoinColumn(name = "id_entrenador", nullable = false)
	private Usuario entrenador; // Usuario que es calificado (entrenador)

	@Column(nullable = false)
	private Integer calificacion; // Calificación numérica (1 a 5)

	private String comentario; // Comentario opcional del ciclista

	@Column(name = "fecha_calificacion", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fechaCalificacion;

	// Constructor
	public Calificacion() {
		this.fechaCalificacion = new java.util.Date(); // Asignar la fecha actual
	}
}
