package com.backend.salvapets.api.controllers;

import com.backend.salvapets.api.dto.PetDTO;
import com.backend.salvapets.domain.model.FotoPet;
import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.service.PetService;
import com.backend.salvapets.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PetService service;

    public PetController(PetService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Pet> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pet> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pet salvar(@RequestBody PetDTO pet) {
        Pet newPet = new Pet();
        newPet.setNome(pet.getNome());
        newPet.setRaca(pet.getRaca());
        newPet.setPorte(pet.getPorte());
        newPet.setIdade(pet.getIdade());
        newPet.setDescricao(pet.getDescricao());

        Usuario usuario = usuarioService.buscarPorId(pet.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        newPet.setUsuario(usuario);

        List<FotoPet> fotos = pet.getFotos().stream().map(f -> {
            FotoPet novaFoto = new FotoPet();
            novaFoto.setImagemBase64(f.getImagemBase64());
            novaFoto.setPet(newPet);
            return novaFoto;
        }).toList();

        newPet.setFotos(fotos);

        return service.salvar(newPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}