package org.example.petclinictest.businesslayer.mappers;

import org.example.petclinictest.businesslayer.Owner;
import org.example.petclinictest.businesslayer.OwnerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    public Owner mapToOwner(OwnerDTO dto);

    public OwnerDTO mapToOwnerDTO(Owner owner);
}
