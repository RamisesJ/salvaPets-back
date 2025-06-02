package com.backend.salvapets.domain.service;

import com.backend.salvapets.domain.model.Ong;

import com.backend.salvapets.domain.repositories.OngRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OngService {

    private final OngRepository ongRepository;

    public OngService(OngRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    public List<Ong> listarTodas() {
        return ongRepository.findAll();
    }

    public Optional<Ong> buscarPorId(Long id) {
        return ongRepository.findById(id);
    }

    @Transactional
    public Ong atualizar(Long id, Ong ongAtualizada) {
        Ong ongExistente = ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));

        ongExistente.setNome(ongAtualizada.getNome());
        ongExistente.setCnpj(ongAtualizada.getCnpj());
        ongExistente.setContato(ongAtualizada.getContato());

        return ongRepository.save(ongExistente);
    }

    public Ong salvar(Ong ong) {
        return ongRepository.save(ong);
    }

    public void deletar(Long id) {
        ongRepository.deleteById(id);
    }
}