package BackendSiadseUfps.siadse.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import BackendSiadseUfps.siadse.entity.OurUsers;

import BackendSiadseUfps.siadse.entity.Semillero;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private String codigoUniversidad;
    private Integer semestreActual;
    private Integer edad;
    private String direccionResidencia;
    private String celular;
   
    private List<Semillero> semillero;
    private OurUsers ourUsers;
    
}
