package org.example.petclinictest.businesslayer.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.petclinictest.businesslayer.NamedEntity;
import org.example.petclinictest.businesslayer.owner.Owner;

@Entity
public class Pet extends NamedEntity {
    String petType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Owner owner;

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
