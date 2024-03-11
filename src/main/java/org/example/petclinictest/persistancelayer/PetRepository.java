package org.example.petclinictest.persistancelayer;

import org.example.petclinictest.businesslayer.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
