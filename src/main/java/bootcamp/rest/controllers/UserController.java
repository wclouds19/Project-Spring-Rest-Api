package bootcamp.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.rest.dto.AuthDto;
import bootcamp.rest.helpers.ResponseHelpers;
import bootcamp.rest.models.entities.UserEntity;
import bootcamp.rest.security.JwtServiceSecurity;
import bootcamp.rest.services.UserServices;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userService;

    @Autowired
    private JwtServiceSecurity jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public ModelMapper modelMapper;

    /* 
     * Feature : Registration
    */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserEntity user) {
        try {

            Map<String, Object> responData = new HashMap<String, Object>();
            
            responData.put("user", userService.create(user));
            responData.put("status", "BELUM TERVALIDASI");         

            return ResponseHelpers.generateResponse(HttpStatus.OK, true, "Success", responData);
        }catch (Exception e) {
            return ResponseHelpers.generateResponse(HttpStatus.UNAUTHORIZED, false, e.getMessage(), null);
        }        
    }

    @GetMapping("/member_status/{email}") 
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> memberStatus(@PathVariable("email") String email) {
        try {

            Map<String, Object> responData = new HashMap<String, Object>();
            
            responData.put("user", userService.checkStatus(email));

            int status = userService.checkStatus(email).getStatus();
            String statusDesc = "";

            if(status == 0){statusDesc = "You Are Not Registered";}
            else if(status == 1){statusDesc = "You Are Not Validated";}
            else if(status == 2){statusDesc = "You Are Registered";}

            responData.put("status", statusDesc);         

            return ResponseHelpers.generateResponse(HttpStatus.OK, true, "Success", responData);
        }catch (Exception e) {
            return ResponseHelpers.generateResponse(HttpStatus.UNAUTHORIZED, false, e.getMessage(), null);
        }        
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> findAll(){       
        return ResponseHelpers.generateResponse(HttpStatus.OK, true, "Success", userService.findAllUser());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> findOneUser(@PathVariable("id") Long Id){
        
        try{
            return ResponseHelpers.generateResponse(HttpStatus.OK, true, "Success", userService.findOneUser(Id));
        }catch (Exception e) {
            return ResponseHelpers.generateResponse(HttpStatus.NOT_FOUND, false, e.getMessage(), null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long Id, @RequestBody UserEntity user) {
        try {
            return ResponseHelpers.generateResponse(HttpStatus.OK, true, "Success", userService.update(user, Id));
        }catch (Exception e) {
            return ResponseHelpers.generateResponse(HttpStatus.NOT_FOUND, false, e.getMessage(), null);
        }        
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteOneUser(@PathVariable("id") Long Id){  
        userService.removeOneUser(Id);
    }

    /* 
     * Feature : Authentication
    */
    @PostMapping("/auth")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthDto authRequest) {

        Map<String, Object> responData = new HashMap<String, Object>();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            
        if(authentication.isAuthenticated()){
            responData.put("email", authRequest.getEmail());
            responData.put("token", jwtService.generateToken(authRequest.getEmail()));
            responData.put("expired", jwtService.extractExpiration(jwtService.generateToken(authRequest.getEmail())));

            String message = String.format("Successfully to create token User with email : '%s'", authRequest.getEmail());

            return ResponseHelpers.generateResponse(HttpStatus.OK, true, message, responData);   
        }

        return null;
    }
}
