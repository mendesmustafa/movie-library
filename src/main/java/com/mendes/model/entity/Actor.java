package com.mendes.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mendes.enums.ActorRole;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Entity
@Table(name = "ACTOR")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Actor.class)
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ACTOR_ID_SEQ")
    @SequenceGenerator(name = "ACTOR_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private ActorRole role = ActorRole.LEAD_ACTOR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ActorRole getRole() {
        return role;
    }

    public void setRole(ActorRole role) {
        this.role = role;
    }
}
