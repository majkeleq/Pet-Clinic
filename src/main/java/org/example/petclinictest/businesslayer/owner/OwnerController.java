package org.example.petclinictest.businesslayer.owner;


import org.example.petclinictest.businesslayer.exceptions.OwnerNotFoundException;
import org.example.petclinictest.businesslayer.mappers.OwnerMapper;
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

    public OwnerController(OwnerRepository ownerRepository, OwnerMapper ownerMapper, OwnerModelAssembler assembler) {

        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
        this.assembler = assembler;
    }

    @GetMapping
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/owners")
    public CollectionModel<EntityModel<OwnerDTO>> getAllOwners() {
        List<EntityModel<OwnerDTO>> owners = ownerRepository.findAll()
                .stream()
                .map(ownerMapper::mapToOwnerDTO)
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(owners, linkTo(methodOn(OwnerController.class).getAllOwners()).withSelfRel());
    }

    @GetMapping("/owners/{id}")
    public EntityModel<OwnerDTO> getOwner(@PathVariable Long id) {
        OwnerDTO dto = ownerMapper.mapToOwnerDTO(ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id)));
        return assembler.toModel(dto);
    }

    @PostMapping("/owners")
    public ResponseEntity<?> addOwner(@RequestBody OwnerDTO dto) {
        Owner newOwner = ownerMapper.mapToOwner(dto);

        EntityModel<OwnerDTO> entityModel = assembler.toModel(ownerMapper.mapToOwnerDTO(ownerRepository.save(newOwner)));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
