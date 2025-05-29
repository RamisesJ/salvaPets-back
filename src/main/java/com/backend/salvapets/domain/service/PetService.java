package com.backend.salvapets.domain.service;

import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public List<Pet> listarPorUsuario(Long usuarioId) {
        return petRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Pet> buscarPorId(Long id) {
        return petRepository.findById(id);
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletar(Long id) {
        petRepository.deleteById(id);
    }
}