package com.backend.salvapets.domain.service;

import com.backend.salvapets.domain.model.FotoPet;
import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.repositories.FotoPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoPetService {
    private final FotoPetRepository fotoPetRepository;

    @Autowired
    public FotoPetService(FotoPetRepository fotoPetRepository) {
        this.fotoPetRepository = fotoPetRepository;
    }

    public FotoPet salvar(FotoPet fotoPet) {
        return fotoPetRepository.save(fotoPet);
    }

    public List<FotoPet> listarTodas() {
        return fotoPetRepository.findAll();
    }

    public Optional<FotoPet> buscarPorId(Long id) {
        return fotoPetRepository.findById(id);
    }

    public void deletar(Long id) {
        fotoPetRepository.deleteById(id);
    }

    public List<FotoPet> buscarPorPetId(Pet pet) {
        return fotoPetRepository.findByPet(pet);
    }
}
