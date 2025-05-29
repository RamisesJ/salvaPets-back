package com.backend.salvapets.domain.repositories;

import com.backend.salvapets.domain.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}