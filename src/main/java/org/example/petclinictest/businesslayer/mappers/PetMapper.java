package org.example.petclinictest.businesslayer.mappers;

import org.example.petclinictest.businesslayer.pet.Pet;
import org.example.petclinictest.businesslayer.pet.PetDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    Pet mapToPet(PetDTO dto);
    PetDTO mapToPetDTO(Pet pet);
}
