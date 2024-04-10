package BackendSiadseUfps.siadse.service;

import java.util.List;

import BackendSiadseUfps.siadse.entity.Usuarios;

public interface IUsuariosService {
	
		void guardar(Usuarios usuario);
		void eliminar(Integer idUsuario);
		List<Usuarios> buscarTodos();
		List<Usuarios> buscarRegistrados();
		Usuarios buscarPorId(Integer idUsuario);
		Usuarios buscarPorUsername(String username);
		int bloquear(int idUsuario);
		int activar(int idUsuario);
	}

