package org.example.petclinictest.businesslayer.pet;

import org.example.petclinictest.persistancelayer.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping()
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
