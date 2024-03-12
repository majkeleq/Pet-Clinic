package org.example.petclinictest.businesslayer.exceptions;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException(Long id) {
        super("Could not find pet with id = " + id);
    }
}
