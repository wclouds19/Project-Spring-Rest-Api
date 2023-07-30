package bootcamp.rest.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.rest.dto.AuthRequest;
import bootcamp.rest.dto.ResponDataDto;
import bootcamp.rest.dto.UserDto;
import bootcamp.rest.models.entities.User;
import bootcamp.rest.security.JwtService;
import bootcamp.rest.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome User this endpoint is not secure";
    }

    @GetMapping("/welcome2")
    public String welcome2() {
        return "Unauthorized";
    }

    @PostMapping("/register")
    public ResponseEntity<ResponDataDto<User>> register(@RequestBody UserDto userDto /* , Errors errors*/){

        ResponDataDto<User> responData = new ResponDataDto<>();

        /* 
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }*/

        User userEntity = modelMapper.map(userDto, User.class);

        responData.setStatus(true);
        responData.getMessages().add("User Register Succesfully");
        responData.setdata(userService.register(userEntity));
        return ResponseEntity.ok(responData);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
