package BackendSiadseUfps.siadse.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.dto.ReqRes;
import BackendSiadseUfps.siadse.entity.Usuarios;
import BackendSiadseUfps.siadse.repository.UsuariosRepo;

@Service
public class AuthService {

	@Autowired
	private UsuariosRepo usuariosRepo;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public ReqRes signUp(ReqRes registrationRequest) {
		ReqRes resp = new ReqRes();
		try {
			Usuarios ourUsers = new Usuarios();
			ourUsers.setEmail(registrationRequest.getEmail());
			ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			ourUsers.setRole(registrationRequest.getRole());
			ourUsers.setCodigo(registrationRequest.getCodigoUniversidad());
			ourUsers.setSemestreActual(registrationRequest.getSemestreActual());
			ourUsers.setEdad(registrationRequest.getEdad());
			ourUsers.setDireccionResidencia(registrationRequest.getDireccionResidencia());
			ourUsers.setCelular(registrationRequest.getCelular());
			Usuarios ourUserResult =usuariosRepo.save(ourUsers);
			if (ourUserResult != null && ourUserResult.getId_usuario() > 0) {
				resp.setUsuarios(ourUserResult);
				resp.setMessage("Usuario guardado satisfatoriamente");
				resp.setStatusCode(200);
			}
		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}
	
	

	

	public ReqRes signIn(ReqRes signinRequest) {
		ReqRes response = new ReqRes();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
			var user =usuariosRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
			System.out.println("USER IS: " + user);
			var jwt = jwtUtils.generateToken(user);
			var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshToken);
			response.setExpirationTime("24Hr");
			response.setMessage("Iniciado sesión correctamente");
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setError(e.getMessage());
		}
		return response;
	}

	public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
		ReqRes response = new ReqRes();
		String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
		Usuarios users =usuariosRepo.findByEmail(ourEmail).orElseThrow();
		if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
			var jwt = jwtUtils.generateToken(users);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshTokenReqiest.getToken());
			response.setExpirationTime("24Hr");
			response.setMessage("Token actualizado correctamente");
		}
		response.setStatusCode(500);
		return response;
	}
}