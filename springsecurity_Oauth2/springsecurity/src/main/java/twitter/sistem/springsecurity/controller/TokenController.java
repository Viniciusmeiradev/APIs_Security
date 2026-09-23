package twitter.sistem.springsecurity.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import twitter.sistem.springsecurity.controller.dto.LoginRequest;
import twitter.sistem.springsecurity.controller.dto.LoginResponse;
import twitter.sistem.springsecurity.repository.UserRepository;

@RestController
public class TokenController {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository){
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        var user = userRepository.findBynome(loginRequest.nome());

        if(user.isEmpty()){
            throw new BadCredentialsException("Usuario ou senha inválido!");
        }
    }
}
