package com.backend.salvapets.api.controllers;

import com.backend.salvapets.api.dto.PetDTO;
import com.backend.salvapets.domain.exception.Response;
import com.backend.salvapets.domain.model.FotoPet;
import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.service.FotoPetService;
import com.backend.salvapets.domain.service.PetService;
import com.backend.salvapets.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PetService service;
    @Autowired
    private FotoPetService fotoPetService;

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

    @PostMapping("/{id}/fotos")
    public ResponseEntity<Response<FotoPet>> uploadFoto(
            @PathVariable("id") Long id,
            @RequestParam("fotos") List<MultipartFile> fotos) {

        Response<FotoPet> response = new Response<>();
        Optional<Pet> optionalPet = service.buscarPorId(id);

        if (!optionalPet.isPresent()) {
            response.getErrors().add("Pet com ID " + id + " não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Pet pet = service.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Pet com ID " + id + " não encontrado."));

        fotos.forEach(foto -> {
            try {
                FotoPet fotoPet = new FotoPet();
                fotoPet.setPet(pet);
                fotoPet.setImagemBase64(foto.getBytes() != null ? new String(foto.getBytes()) : null);
                fotoPetService.salvar(fotoPet);
            } catch (IOException e) {
                e.printStackTrace();
                response.getErrors().add("Erro ao processar a foto: " + e.getMessage());
            }
        });

        if (response.getErrors().isEmpty()) {
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}