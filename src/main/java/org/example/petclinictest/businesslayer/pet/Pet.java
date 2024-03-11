package org.example.petclinictest.businesslayer.pet;

import jakarta.persistence.Entity;
import org.example.petclinictest.businesslayer.NamedEntity;

@Entity
public class Pet extends NamedEntity {
    String petType;

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
