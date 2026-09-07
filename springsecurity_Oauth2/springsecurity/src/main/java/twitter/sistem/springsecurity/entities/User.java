package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;

import java.lang.annotation.Inherited;
import java.util.UUID;

@Entity
@Table(name = "table_users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String username;
    private String password;
    private Set<Role> roles;
}