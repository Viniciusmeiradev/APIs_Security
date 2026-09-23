package twitter.sistem.springsecurity.controller;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import twitter.sistem.springsecurity.controller.dto.LoginRequest;
import twitter.sistem.springsecurity.controller.dto.LoginResponse;
import twitter.sistem.springsecurity.repository.UserRepository;

@RestController
public class TokenController {
    private final JwtEncoder jwtEnconder;
    private final UserRepository userRepository;

    public TokenController(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        userRepository.findBynome(loginRequest.nome());
    }
}

