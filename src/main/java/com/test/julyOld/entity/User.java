package com.test.julyOld.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity(name = "User")
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    @Column(name = "name")
    private String name;
}
