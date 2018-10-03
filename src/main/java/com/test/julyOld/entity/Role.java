package com.test.julyOld.entity;

import com.test.julyOld.misc.RoleType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Entity(name = "Role")
@Table(name = "role")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity  implements GrantedAuthority {

    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType type;


    @Override
    public String getAuthority() {
        return this.type.name();
    }
}
