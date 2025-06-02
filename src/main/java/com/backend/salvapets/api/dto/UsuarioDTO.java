package com.backend.salvapets.api.dto;

import com.backend.salvapets.domain.model.Contato;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String password;
    private Contato contato;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String cpf, String email, String password, Contato contato) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.contato = contato;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}