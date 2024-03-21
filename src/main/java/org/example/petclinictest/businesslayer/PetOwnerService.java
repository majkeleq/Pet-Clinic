package org.example.petclinictest.businesslayer;

import org.example.petclinictest.businesslayer.exceptions.OwnerNotFoundException;
import org.example.petclinictest.businesslayer.mappers.OwnerMapper;
import org.example.petclinictest.businesslayer.mappers.PetMapper;
import org.example.petclinictest.businesslayer.owner.Owner;
import org.example.petclinictest.businesslayer.owner.OwnerDTO;
import org.example.petclinictest.businesslayer.pet.Pet;
import org.example.petclinictest.businesslayer.pet.PetDTO;
import org.example.petclinictest.persistancelayer.OwnerRepository;
import org.example.petclinictest.persistancelayer.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetOwnerService {
    OwnerRepository ownerRepository;
    PetRepository petRepository;
    OwnerMapper ownerMapper;
    PetMapper petMapper;

    public PetOwnerService(OwnerRepository ownerRepository, PetRepository petRepository, OwnerMapper ownerMapper, PetMapper petMapper) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.ownerMapper = ownerMapper;
        this.petMapper = petMapper;
    }

    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream().map(ownerMapper::mapToOwnerDTO).toList();
    }
    public OwnerDTO getOwner(Long id) {
        return ownerMapper.mapToOwnerDTO(ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id)));
    }
    public OwnerDTO addOwner(OwnerDTO dto) {
        Owner newOwner = ownerMapper.mapToOwner(dto);
        return ownerMapper.mapToOwnerDTO(ownerRepository.save(newOwner));
    }
    public OwnerDTO addPet(PetDTO petDTO, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId));
        Pet pet = petMapper.mapToPet(petDTO);
        pet.setOwner(owner);
        petRepository.save(pet);
        return ownerMapper.mapToOwnerDTO(owner);
    }
}
