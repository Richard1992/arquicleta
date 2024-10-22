package aes.arquicleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotNull(message = "El apellido no puede ser nulo")
    @Size(min = 1, message = "El apellido no puede estar vacío")
    private String apellido;
    
    @NotNull(message = "El correo no puede ser nulo")
    @Email(message = "El correo debe ser válido")
    private String correo;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;
    
    @NotNull(message = "La dirección no puede ser nula")
    @Size(min = 1, message = "La dirección no puede estar vacío")
    private String direccion;
    
    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private Integer edad;

    @NotNull(message = "El género no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(min = 1, message = "El teléfono no puede estar vacío")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = false)
    @NotNull(message = "La ciudad no puede ser nula")
    private Ciudad ciudad;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaRegistro;

    // Constructor
    public Usuario() {
        this.fechaRegistro = new java.util.Date();
    }

    // Enum para el género
    public enum Genero {
        Masculino,
        Femenino,
        Otro
    }
}
