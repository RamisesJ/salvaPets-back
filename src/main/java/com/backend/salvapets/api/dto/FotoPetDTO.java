package com.backend.salvapets.api.dto;

public class FotoPetDTO {

    private Long id;
    private String imagemBase64;

    public FotoPetDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }
}
