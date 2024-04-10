package BackendSiadseUfps.siadse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken {
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;
	private String token;
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id", referencedColumnName = "codigo")
	private Usuarios usuario ;
	private Instant fechaExpiracion;
}
