package org.example.petclinictest.businesslayer.mappers;

import org.example.petclinictest.businesslayer.pet.Pet;
import org.example.petclinictest.businesslayer.pet.PetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {
    Pet mapToPet(PetDTO dto);
    @Mapping(source = "owner.id", target = "ownerId")
    PetDTO mapToPetDTO(Pet pet);
}
