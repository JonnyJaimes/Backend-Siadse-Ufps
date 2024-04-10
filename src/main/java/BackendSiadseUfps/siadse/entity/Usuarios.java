package BackendSiadseUfps.siadse.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
@Table(name = "usuarios")
public class Usuarios implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usuario;
	@NotNull(message = "email cannot be null")
	@Size(min = 1, max = 255)
	private String email;
	@NotNull(message = "passwod cannot be null")
	@Size(min = 6, max = 255)
	private String password;
	@NotNull(message = "Role cannot be null")
	private String role;
	@NotNull(message = "codigoU cannot be null")
	@Size(min = 1, max = 255)
	private String codigo;
	@NotNull(message = "semestreActual cannot be null")
	@Min(1)
	@Max(85)
	private Integer semestreActual;
	@NotNull(message = "edad cannot be null")
	@Min(16)
	private Integer edad;
	@NotNull(message = "Direccion cannot be null")
	@Size(min = 1, max = 255)
	private String direccionResidencia;
	@NotNull(message = "celular cannot be null")
	@Size(min = 1, max = 15)
	private String celular;
	private Date fechaRegistro;
	private String imagen = "no-image.png";
	private Integer estatus;

	private boolean directorSemilleros;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "codigo"), inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id"))
	private List<Rol> roles;

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));

		// Si el usuario es director de semilleros, agregamos el rol correspondiente
		if (directorSemilleros) {
			authorities.add(new SimpleGrantedAuthority("ROLE_DIRECTOR_SEMILLEROS"));
		}

		return authorities;
	}

	// Metodo para agregar perfiles
	public void agregar(Rol tempPerfil) {
		if (roles == null) {
			roles = new LinkedList<>();
		}
		roles.add(tempPerfil);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}