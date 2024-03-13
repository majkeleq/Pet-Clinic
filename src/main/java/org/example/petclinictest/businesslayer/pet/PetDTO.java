package org.example.petclinictest.businesslayer.pet;

import org.example.petclinictest.businesslayer.owner.OwnerDTO;

public class PetDTO {
    private Long id;
    private String name;
    private String petType;
    private OwnerDTO owner;

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
