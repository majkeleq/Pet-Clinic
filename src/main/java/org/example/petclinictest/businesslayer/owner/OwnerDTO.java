package org.example.petclinictest.businesslayer.owner;

import org.example.petclinictest.businesslayer.pet.PetDTO;

import java.util.List;

public class OwnerDTO {
    private Long id;
    private String name;
    private String lastName;
    private List<PetDTO> pets;

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
