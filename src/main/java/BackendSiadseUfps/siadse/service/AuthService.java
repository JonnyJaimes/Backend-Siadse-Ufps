package BackendSiadseUfps.siadse.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.dto.ReqRes;
import BackendSiadseUfps.siadse.entity.OurUsers;
import BackendSiadseUfps.siadse.repository.OurUserRepo;

@Service
public class AuthService {

	@Autowired
	private OurUserRepo ourUserRepo;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public ReqRes signUp(ReqRes registrationRequest) {
		ReqRes resp = new ReqRes();
		try {
			OurUsers ourUsers = new OurUsers();
			ourUsers.setEmail(registrationRequest.getEmail());
			ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			ourUsers.setRole(registrationRequest.getRole());
			ourUsers.setCodigoUniversidad(registrationRequest.getCodigoUniversidad());
			ourUsers.setSemestreActual(registrationRequest.getSemestreActual());
			ourUsers.setEdad(registrationRequest.getEdad());
			ourUsers.setDireccionResidencia(registrationRequest.getDireccionResidencia());
			ourUsers.setCelular(registrationRequest.getCelular());
			OurUsers ourUserResult = ourUserRepo.save(ourUsers);
			if (ourUserResult != null && ourUserResult.getId() > 0) {
				resp.setOurUsers(ourUserResult);
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
			var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
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
		OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
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