package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String rolenome;

    public Long getRoleId(){
        return roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public String getRolenome(){
        return rolenome;
    }
    public void setRolenome(String rolenome){
        this.rolenome = rolenome;
    }

    public enum Values{
        ADMIN(1L),
        BASIC(2L);
        long roleId;

        Values(Long roleId){
            this.roleId = roleId;
        }
        public Long getRoleId(){
            return roleId;
        }
    }
}
