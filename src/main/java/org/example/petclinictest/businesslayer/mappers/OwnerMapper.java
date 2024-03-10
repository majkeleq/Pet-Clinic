package org.example.petclinictest.businesslayer.mappers;

import org.example.petclinictest.businesslayer.owner.Owner;
import org.example.petclinictest.businesslayer.owner.OwnerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner mapToOwner(OwnerDTO dto);

    OwnerDTO mapToOwnerDTO(Owner owner);
}
