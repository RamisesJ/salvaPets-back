package com.backend.salvapets.domain.repositories;

import com.backend.salvapets.domain.model.FotoPet;
import com.backend.salvapets.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoPetRepository extends JpaRepository<FotoPet, Long> {
    List<FotoPet> findByPet(Pet pet);
    boolean existsByIdAndPet(Long id, Pet pet);
    void deleteByPet(Pet pet);
    void deleteById(Long id);
    void deleteByPetId(Long petId);
}