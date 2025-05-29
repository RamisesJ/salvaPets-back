package com.backend.salvapets.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ong {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    @Column(name = "razao_social")
    private String razaoSocial;
    private String descricao;
    private String site;
    private String email;

    @OneToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioOng> usuarioOng = new ArrayList<>();


}
