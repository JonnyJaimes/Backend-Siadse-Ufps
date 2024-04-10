package BackendSiadseUfps.siadse.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.entity.Usuarios;
import BackendSiadseUfps.siadse.repository.UsuariosRepo;
import BackendSiadseUfps.siadse.service.IUsuariosService;
import jakarta.transaction.Transactional;

@Service
public class UsuariosServiceJpa implements IUsuariosService{

	@Autowired
	private UsuariosRepo usuariosRepo;
	
	@Override
	public void guardar(Usuarios usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuarios> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public Usuarios buscarPorId(Integer idUsuario) {
		Optional<Usuarios> optional = usuariosRepo.findById(idUsuario);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Usuarios buscarPorUsername(String username) {
		return usuariosRepo.findByUsername(username);
	}

	@Override
	public List<Usuarios> buscarRegistrados() {		
		return usuariosRepo.findByFechaRegistroNotNull();
	}

	@Transactional
	@Override
	public int bloquear(int idUsuario) {
		int rows = usuariosRepo.lock(idUsuario);
		return rows;
	}

	@Transactional
	@Override
	public int activar(int idUsuario) {
		int rows = usuariosRepo.unlock(idUsuario);
		return rows;
	}

}