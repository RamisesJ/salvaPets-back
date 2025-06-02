package com.backend.salvapets.api.dto;

import com.backend.salvapets.domain.model.Perfil;

public record ResponseDTO (Long id, String nome, Perfil perfil, String token) {
}
