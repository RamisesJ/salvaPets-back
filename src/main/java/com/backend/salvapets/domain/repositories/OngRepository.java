package com.backend.salvapets.domain.repositories;

import com.backend.salvapets.domain.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong, Long> {
    Optional<Ong> findByCnpj(String cnpj);
}