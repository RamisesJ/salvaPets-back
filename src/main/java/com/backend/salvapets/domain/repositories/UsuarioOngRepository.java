package com.backend.salvapets.domain.repositories;

import com.backend.salvapets.domain.model.UsuarioOng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioOngRepository extends JpaRepository<UsuarioOng, Long> {
    List<UsuarioOng> findByUsuarioId(Long usuarioId);
    List<UsuarioOng> findByOngId(Long ongId);
    Optional<UsuarioOng> findByUsuarioIdAndOngId(Long usuarioId, Long ongId);
}