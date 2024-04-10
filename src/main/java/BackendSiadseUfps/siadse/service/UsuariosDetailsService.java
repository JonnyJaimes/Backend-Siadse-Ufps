package BackendSiadseUfps.siadse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.repository.UsuariosRepo;

@Service
public class UsuariosDetailsService implements UserDetailsService {

    @Autowired
    private UsuariosRepo usuariosRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuariosRepo.findByEmail(username).orElseThrow();
    }
}