package com.backend.salvapets.api.dto;

import java.util.List;


public class PetDTO {

    private Long id;
    private String nome;
    private String raca;
    private String porte;
    private String sexo;
    private String idade;
    private String descricao;
    private Long usuarioId;
    private List<FotoPetDTO> fotos;

    public PetDTO() {
    }

    public PetDTO(Long id, String nome, String raca, String porte, String sexo, String idade, String descricao, Long usuarioId, List<FotoPetDTO> fotos) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.idade = idade;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public List<FotoPetDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoPetDTO> fotos) {
        this.fotos = fotos;
    }
}
