package org.example.petclinictest.businesslayer;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends NamedEntity{
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
