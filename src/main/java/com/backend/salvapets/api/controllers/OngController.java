package com.backend.salvapets.api.controllers;

import com.backend.salvapets.domain.model.Ong;
import com.backend.salvapets.domain.service.OngService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ongs")
public class OngController {

    private final OngService service;

    public OngController(OngService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ong> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ong> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ong salvar(@RequestBody Ong ong) {
        return service.salvar(ong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}