package twitter.sistem.springsecurity.entities;

import java.lang.annotation.Inherited;

import jakarta.persistence.*;

@Entity
@Table(name="tb_roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GeneratedType.IDENTITY)
    private Long roleId;

    private String nome;
}
