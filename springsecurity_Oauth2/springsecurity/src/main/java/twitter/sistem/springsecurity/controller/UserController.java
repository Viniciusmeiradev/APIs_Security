package twitter.sistem.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import twitter.sistem.springsecurity.controller.dto.CreateUserDto;
import twitter.sistem.springsecurity.repository.RoleRepository;
import twitter.sistem.springsecurity.repository.UserRepository;

@RestController 
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto){
        roleRepository.findByName(Role.Value.BASIC.name());
        return ResponseEntity.ok().build();
    }
}
