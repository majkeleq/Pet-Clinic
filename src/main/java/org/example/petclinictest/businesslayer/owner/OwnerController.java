package org.example.petclinictest.businesslayer.owner;


import org.example.petclinictest.businesslayer.PetOwnerService;
import org.example.petclinictest.businesslayer.exceptions.OwnerNotFoundException;
import org.example.petclinictest.businesslayer.mappers.OwnerMapper;
import org.example.petclinictest.businesslayer.pet.PetDTO;
import org.example.petclinictest.persistancelayer.OwnerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OwnerController {

    OwnerRepository ownerRepository;
    OwnerMapper ownerMapper;
    OwnerModelAssembler assembler;
    PetOwnerService petOwnerService;

    public OwnerController(OwnerRepository ownerRepository, OwnerMapper ownerMapper, OwnerModelAssembler assembler, PetOwnerService petOwnerService) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
        this.assembler = assembler;
        this.petOwnerService = petOwnerService;
    }

    @GetMapping
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/owners")
    public CollectionModel<EntityModel<OwnerDTO>> getAllOwners() {
        List<EntityModel<OwnerDTO>> owners = petOwnerService.getAllOwners()
                .stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(owners, linkTo(methodOn(OwnerController.class).getAllOwners()).withSelfRel());
    }

    @GetMapping("/owners/{id}")
    public EntityModel<OwnerDTO> getOwner(@PathVariable Long id) {
        OwnerDTO dto = petOwnerService.getOwner(id);
        return assembler.toModel(dto);
    }

    @PostMapping("/owners")
    public ResponseEntity<?> addOwner(@RequestBody OwnerDTO dto) {

        EntityModel<OwnerDTO> entityModel = assembler.toModel(petOwnerService.addOwner(dto));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/owners/{ownerId}/pet")
    public ResponseEntity<?> addPet(@RequestBody PetDTO dto, @PathVariable Long ownerId) {
        EntityModel<OwnerDTO> entityModel = assembler.toModel(petOwnerService.addPet(dto, ownerId));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
