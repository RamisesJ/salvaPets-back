package com.backend.salvapets.domain.repositories;


import com.backend.salvapets.domain.model.Perfil;
import com.backend.salvapets.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByPerfil(Perfil perfil);
    Optional<Usuario> findByCpf(String cpf);
}