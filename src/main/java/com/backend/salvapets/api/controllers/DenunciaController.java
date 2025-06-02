package com.backend.salvapets.api.controllers;

import com.backend.salvapets.domain.exception.Response;
import com.backend.salvapets.domain.model.Denuncia;
import com.backend.salvapets.domain.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping
    public ResponseEntity<Denuncia> salvar(@RequestBody Denuncia denuncia) {
        try {
            Denuncia salva = denunciaService.salvar(denuncia);
            return ResponseEntity.ok(salva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Denuncia>> listarTodas() {
        try {
            List<Denuncia> denuncias = denunciaService.listarTodas();
            return ResponseEntity.ok(denuncias);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
