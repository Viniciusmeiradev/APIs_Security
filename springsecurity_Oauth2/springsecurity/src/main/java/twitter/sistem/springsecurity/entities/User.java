package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String nome;
    private String senha;
    private Set<Role> roles;
}
