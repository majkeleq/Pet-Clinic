package org.example.petclinictest.businesslayer;

import org.example.petclinictest.businesslayer.mappers.OwnerMapper;
import org.example.petclinictest.persistancelayer.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    OwnerRepository ownerRepository;
    OwnerMapper ownerMapper;
    public ApiController(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {

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
    public OwnerDTO getOwner(@PathVariable Long id) {
        return ownerMapper.mapToOwnerDTO(ownerRepository.findById(id).orElse(null));
    }

    @PostMapping("/owners")
    public Owner addOwner(@RequestBody OwnerDTO dto) {
        Owner newOwner = ownerMapper.mapToOwner(dto);
        System.out.println(newOwner.getId());
        System.out.println(newOwner.getName());
        System.out.println(newOwner.getLastName());
        return ownerRepository.save(newOwner);
    }
}
