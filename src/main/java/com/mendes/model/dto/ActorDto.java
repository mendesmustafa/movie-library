package com.mendes.model.dto;

import com.mendes.enums.ActorRole;
import java.io.Serializable;

/**
 * @author mendesmustafa on 23-04-2022
 */

public class ActorDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private ActorRole role;

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
