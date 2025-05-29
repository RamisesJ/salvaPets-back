package com.backend.salvapets.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioOng {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;

    private String funcao;

}
