package BackendSiadseUfps.siadse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import BackendSiadseUfps.siadse.entity.Usuarios;

public interface UsuariosRepo extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByEmail(String email);
    Usuarios findByUsername(String username);
	List<Usuarios> findByFechaRegistroNotNull();
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=0 WHERE u.id = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=1 WHERE u.id = :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);
}