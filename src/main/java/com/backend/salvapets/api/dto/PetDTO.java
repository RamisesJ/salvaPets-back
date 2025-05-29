package com.backend.salvapets.api.dto;


import com.backend.salvapets.domain.model.FotoPet;

import java.util.List;

public class PetDTO {

    private String nome;
    private String raca;
    private String porte;
    private String idade;
    private String descricao;
    private Long usuarioId;
    private List<FotoPet> fotos;

    public PetDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<FotoPet> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoPet> fotos) {
        this.fotos = fotos;
    }
}
