package com.backend.salvapets.domain.repositories;

import com.backend.salvapets.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByUsuarioId(Long usuarioId);
}