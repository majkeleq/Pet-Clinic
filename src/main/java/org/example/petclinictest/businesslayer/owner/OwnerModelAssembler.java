package org.example.petclinictest.businesslayer.owner;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OwnerModelAssembler implements RepresentationModelAssembler<OwnerDTO, EntityModel<OwnerDTO>> {
    @Override
    public EntityModel<OwnerDTO> toModel(OwnerDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(OwnerController.class).getOwner(dto.getId())).withSelfRel(),
                linkTo(methodOn(OwnerController.class).getAllOwners()).withRel("owners"));
    }
}
