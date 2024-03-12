package org.example.petclinictest.businesslayer.pet;

import org.example.petclinictest.businesslayer.exceptions.PetNotFoundException;
import org.example.petclinictest.businesslayer.mappers.PetMapper;
import org.example.petclinictest.persistancelayer.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    PetRepository petRepository;
    PetMapper petMapper;

    public PetController(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @GetMapping("/{id}")

    public PetDTO getPet(@PathVariable Long id) {
        return petMapper.mapToPetDTO(petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id)));
    }
    @GetMapping
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream().map(petMapper::mapToPetDTO).toList();
    }
}
