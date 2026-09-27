package twitter.sistem.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter.sistem.springsecurity.repository.UserRepository;

@RestController 
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Void>newUser(){
        
    }
}
