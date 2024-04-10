package BackendSiadseUfps.siadse.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "solicitud_ingreso_semillero")
public class SolicitudIngresoSemillero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private Date fecha; // Fecha en que aplico el usuario
	private String comentarios;
	 // El nombre del archivo PDF, DOCX del CV.

	@OneToOne
	@JoinColumn(name = "idSemillero") // foreignKey en la tabla de solicitudes
	private Semillero Semillero;

	@OneToOne
	@JoinColumn(name = "idUsuario") // foreignKey en la tabla de usuarios
	private Usuarios usuario;
}
