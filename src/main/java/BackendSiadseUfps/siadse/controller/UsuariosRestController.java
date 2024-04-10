package BackendSiadseUfps.siadse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendSiadseUfps.siadse.service.IUsuariosService;
import BackendSiadseUfps.siadse.entity.Usuarios;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosRestController {
	
    @Autowired
    private IUsuariosService serviceUsuarios;
    
    @GetMapping("/listar")
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        List<Usuarios> listaUsuarios = serviceUsuarios.buscarRegistrados();
        return ResponseEntity.ok(listaUsuarios);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") int idUsuario) {
        serviceUsuarios.eliminar(idUsuario);
        return ResponseEntity.ok("El usuario fue eliminado correctamente.");
    }
    
    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarUsuario(@PathVariable("id") int idUsuario) {
        serviceUsuarios.activar(idUsuario);
        return ResponseEntity.ok("El usuario fue activado y ahora tiene acceso al sistema.");
    }
    
    @PutMapping("/bloquear/{id}")
    public ResponseEntity<String> bloquearUsuario(@PathVariable("id") int idUsuario) {
        serviceUsuarios.bloquear(idUsuario);
        return ResponseEntity.ok("El usuario fue bloqueado y no tendrá acceso al sistema.");
    }
}
