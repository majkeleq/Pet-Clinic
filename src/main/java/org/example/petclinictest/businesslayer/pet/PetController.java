package org.example.petclinictest.businesslayer.pet;

import org.example.petclinictest.businesslayer.mappers.PetMapper;
import org.example.petclinictest.persistancelayer.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream().map(petMapper::mapToPetDTO).toList();
    }
}
