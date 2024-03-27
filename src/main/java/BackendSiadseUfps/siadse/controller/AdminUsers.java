package BackendSiadseUfps.siadse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BackendSiadseUfps.siadse.dto.ReqRes;
import BackendSiadseUfps.siadse.entity.Semillero;
import BackendSiadseUfps.siadse.repository.SemilleroRepo;


@RestController
public class AdminUsers {

    @Autowired
    private SemilleroRepo semilleroRepo;

    @GetMapping("/public/semilleros")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(semilleroRepo.findAll());
    }

    @PostMapping("/admin/guardarsemillero")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes productRequest){
        Semillero productToSave = new Semillero();
        productToSave.setName(productRequest.getName());
        return ResponseEntity.ok(semilleroRepo.save(productToSave));
    }


    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("Solo los usuarios pueden acceder a esta API solo.");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi(){
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    /** You can use this to get the details(name,email,role,ip, e.t.c) of user accessing the service*/
    @GetMapping("/public/email")
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication); //get all details(name,email,password,roles e.t.c) of the user
        System.out.println(authentication.getDetails()); // get remote ip
        System.out.println(authentication.getName()); //returns the email because the email is the unique identifier
        return authentication.getName(); // returns the email
    }
}