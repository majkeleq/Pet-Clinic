package org.example.petclinictest.businesslayer.pet;

import org.example.petclinictest.businesslayer.exceptions.PetNotFoundException;
import org.example.petclinictest.businesslayer.mappers.PetMapper;
import org.example.petclinictest.persistancelayer.PetRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pets")
public class PetController {
    PetRepository petRepository;
    PetMapper petMapper;
    PetModelAssembler assembler;

    public PetController(PetRepository petRepository, PetMapper petMapper, PetModelAssembler assembler) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")

    public EntityModel<PetDTO> getPet(@PathVariable Long id) {
        PetDTO dto = petMapper.mapToPetDTO(petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id)));
        return assembler.toModel(dto);
    }
    @GetMapping
    public CollectionModel<EntityModel<PetDTO>> getAllPets() {
        List<EntityModel<PetDTO>> pets = petRepository
                .findAll()
                .stream()
                .map(petMapper::mapToPetDTO)
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(pets, linkTo(methodOn(PetController.class).getAllPets()).withSelfRel());
    }
    @PostMapping
    public ResponseEntity<?> addPet(@RequestBody PetDTO dto) {
        Pet entity = petMapper.mapToPet(dto);
        EntityModel<PetDTO> entityModel = assembler.toModel(petMapper.mapToPetDTO(petRepository.save(entity)));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
