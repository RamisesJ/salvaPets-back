package com.backend.salvapets.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "denuncia")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacao;


    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 letras.")
    private String nome;

    private String cpf;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O telefone deve conter 11 dígitos. (DDD + número)")
    private String telefone;


    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotBlank(message = "O tipo é obrigatório.")
    private String tipo;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaOcorrencia;

    @NotNull
    @PastOrPresent(message = "A data da ocorrência não pode ser futura.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataOcorrencia;

    private String assunto;

    @NotBlank(message = "O relato é obrigatório.")
    @Size(min = 10, message = "O relato deve ter pelo menos 10 caracteres.")
    private String relato;

    @NotNull(message = "A localização é obrigatória.")
    private Double latitude;

    @NotNull(message = "A localização é obrigatória.")
    private Double longitude;

    private String imagem;

    public Denuncia() {
    }

    public Denuncia(Long id, String identificacao, String nome, String cpf, String telefone, String email, String tipo, LocalTime horaOcorrencia, LocalDate dataOcorrencia, String assunto, String relato, Double latitude, Double longitude, String imagem) {
        this.id = id;
        this.identificacao = identificacao;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.horaOcorrencia = horaOcorrencia;
        this.dataOcorrencia = dataOcorrencia;
        this.assunto = assunto;
        this.relato = relato;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo() {
        this.tipo = tipo;
    }

    public LocalTime getHoraOcorrencia() {
        return horaOcorrencia;
    }

    public void setHoraOcorrencia(LocalTime horaOcorrencia) {
        this.horaOcorrencia = horaOcorrencia;
    }

    public LocalDate getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(@NotNull @PastOrPresent(message = "A data da ocorrência não pode ser futura.") LocalDate dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public @NotBlank(message = "O relato é obrigatório.") @Size(min = 10, message = "O relato deve ter pelo menos 10 caracteres.") String getRelato() {
        return relato;
    }

    public void setRelato(@NotBlank(message = "O relato é obrigatório.") @Size(min = 10, message = "O relato deve ter pelo menos 10 caracteres.") String relato) {
        this.relato = relato;
    }

    public @NotNull(message = "A localização é obrigatória.") Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NotNull(message = "A localização é obrigatória.") Double latitude) {
        this.latitude = latitude;
    }

    public @NotNull(message = "A localização é obrigatória.") Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NotNull(message = "A localização é obrigatória.") Double longitude) {
        this.longitude = longitude;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


}