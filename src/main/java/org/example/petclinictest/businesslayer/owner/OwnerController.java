package org.example.petclinictest.businesslayer.owner;


import org.example.petclinictest.businesslayer.exceptions.OwnerNotFoundException;
import org.example.petclinictest.businesslayer.mappers.OwnerMapper;
import org.example.petclinictest.persistancelayer.OwnerRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OwnerController {

    OwnerRepository ownerRepository;
    OwnerMapper ownerMapper;
    public OwnerController(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {

        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping
    public String welcome() {
        return "welcome";
    }
    @GetMapping("/owners")
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream().map(ownerMapper::mapToOwnerDTO).toList();
    }
    @GetMapping("/owners/{id}")
    public EntityModel<OwnerDTO> getOwner(@PathVariable Long id) {
        OwnerDTO dto = ownerMapper.mapToOwnerDTO(ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id)));
        return EntityModel.of(dto,
                linkTo(methodOn(OwnerController.class).getOwner(id)).withSelfRel(),
                linkTo(methodOn(OwnerController.class).getAllOwners()).withRel("owners"));
    }

    @PostMapping("/owners")
    public OwnerDTO addOwner(@RequestBody OwnerDTO dto) {
        Owner newOwner = ownerMapper.mapToOwner(dto);
        System.out.println(newOwner.getId());
        System.out.println(newOwner.getName());
        System.out.println(newOwner.getLastName());
        return ownerMapper.mapToOwnerDTO(ownerRepository.save(newOwner));
    }
}
