package com.backend.salvapets.domain.service;

import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public List<Pet> listarTodos() {
        List<Pet> pets = petRepository.findAll();
        pets.forEach(p -> p.getFotos().size());
        return pets;
    }

    @Transactional
    public List<Pet> buscarPorUsuarioId(Long usuarioId) {
        List<Pet> pets = petRepository.findByUsuarioId(usuarioId);
        pets.forEach(p -> p.getFotos().size());
        return pets;
    }

    @Transactional
    public Pet atualizar(Long id, Pet petAtualizado) {
        Pet petExistente = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        petExistente.setNome(petAtualizado.getNome());
        petExistente.setRaca(petAtualizado.getRaca());
        petExistente.setPorte(petAtualizado.getPorte());
        petExistente.setSexo(petAtualizado.getSexo());
        petExistente.setIdade(petAtualizado.getIdade());
        petExistente.setDescricao(petAtualizado.getDescricao());
        petExistente.setUsuario(petAtualizado.getUsuario());
        petExistente.setFotos(petAtualizado.getFotos());

        return petRepository.save(petExistente);
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