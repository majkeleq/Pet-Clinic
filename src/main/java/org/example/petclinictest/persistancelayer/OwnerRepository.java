package org.example.petclinictest.persistancelayer;

import org.example.petclinictest.businesslayer.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
