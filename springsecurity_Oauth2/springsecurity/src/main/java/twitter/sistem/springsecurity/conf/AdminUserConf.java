package twitter.sistem.springsecurity.conf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.transaction.Transactional;
import twitter.sistem.springsecurity.entities.Role;
import twitter.sistem.springsecurity.repository.RoleRepository;
import twitter.sistem.springsecurity.repository.UserRepository;

@Configuration 
public class AdminUserConf implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConf(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional 
    public void run(String args) throws Exception{
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");
        
        userAdmin.ifPresentOrElse{
            user -> {
                System.out.println("Admin já existe")
            };
            () -> {
                var user = new User();
                user.setUsername("Admin");
                user.setPassword(passwordEncoder.encode(rawPassword:"123"));
                user.setRoles(Set.of(roleAdmin));
            }
        };
    }
}

