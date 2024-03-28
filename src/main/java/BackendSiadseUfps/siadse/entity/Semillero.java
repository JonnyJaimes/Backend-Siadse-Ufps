package BackendSiadseUfps.siadse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "semillero")
public class Semillero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private  Integer id_grupo;
    private Integer id_lider;
    private Integer lineaInvestigacion;
    private String descripcion;
    private String Logo;
    
    
}