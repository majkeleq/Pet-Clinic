package org.example.petclinictest.businesslayer.pet;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PetModelAssembler implements RepresentationModelAssembler<PetDTO, EntityModel<PetDTO>> {
    @Override
    public EntityModel<PetDTO> toModel(PetDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PetController.class).getPet(dto.getId())).withSelfRel(),
                linkTo(methodOn(PetController.class).getAllPets()).withRel("pets"));
    }
}
