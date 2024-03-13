package org.example.petclinictest.businesslayer.owner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.example.petclinictest.businesslayer.Person;
import org.example.petclinictest.businesslayer.pet.Pet;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends Person {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Pet> pets = new ArrayList<>();
}
