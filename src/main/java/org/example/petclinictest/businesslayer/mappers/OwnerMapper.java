package org.example.petclinictest.businesslayer.mappers;

import org.example.petclinictest.businesslayer.owner.Owner;
import org.example.petclinictest.businesslayer.owner.OwnerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(ignore = true, target = "pets")
    Owner mapToOwner(OwnerDTO dto);


    OwnerDTO mapToOwnerDTO(Owner owner);
}
