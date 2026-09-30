package twitter.sistem.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import twitter.sistem.springsecurity.controller.dto.CreateUserDto;
import twitter.sistem.springsecurity.repository.RoleRepository;
import twitter.sistem.springsecurity.repository.UserRepository;
import twitter.sistem.springsecurity.entities.Role;
import twitter.sistem.springsecurity.entities.User;

@RestController 
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @Transactional 
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto){
        var basicRole = roleRepository.findByNome(Role.Values.BASIC.name());
        var userDatabase = userRepository.findByNome(dto.nome());
        if (userDatabase.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        var usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRoles(Set.of(basicRole));
        userRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>>listUser(){
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}

///


