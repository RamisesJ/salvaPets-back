package com.backend.salvapets.api.controllers;

import com.backend.salvapets.api.dto.FotoPetDTO;
import com.backend.salvapets.api.dto.PetDTO;
import com.backend.salvapets.domain.exception.Response;
import com.backend.salvapets.domain.model.FotoPet;
import com.backend.salvapets.domain.model.Pet;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.service.FotoPetService;
import com.backend.salvapets.domain.service.PetService;
import com.backend.salvapets.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FotoPetService fotoPetService;


    @PostMapping
    public ResponseEntity<Pet> salvar(@RequestBody PetDTO petDTO) {
        try {
            Pet pet = new Pet();
            pet.setNome(petDTO.getNome());
            pet.setRaca(petDTO.getRaca());
            pet.setPorte(petDTO.getPorte());
            pet.setSexo(petDTO.getSexo());
            pet.setIdade(petDTO.getIdade());
            pet.setDescricao(petDTO.getDescricao());

            Usuario usuario = usuarioService.buscarPorId(petDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            pet.setUsuario(usuario);

            if (petDTO.getFotos() != null) {
                List<FotoPet> fotos = petDTO.getFotos().stream().map(fotoDTO -> {
                    FotoPet foto = new FotoPet();
                    foto.setImagemBase64(fotoDTO.getImagemBase64());
                    foto.setPet(pet);
                    return foto;
                }).collect(Collectors.toList());
                pet.setFotos(fotos);
            }

            Pet petSalvo = petService.salvar(pet);
            return ResponseEntity.ok(petSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> listarTodos() {

        try {
            List<PetDTO> dtos = petService.listarTodos().stream()
                    .map(this::converterParaDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PetDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<PetDTO> dtos = petService.buscarPorUsuarioId(usuarioId).stream()
                    .map(this::converterParaDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> atualizar(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        try {
            fotoPetService.deletarTodasPorPetId(id);

            Pet pet = petService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

            pet.setNome(petDTO.getNome());
            pet.setRaca(petDTO.getRaca());
            pet.setPorte(petDTO.getPorte());
            pet.setSexo(petDTO.getSexo());
            pet.setIdade(petDTO.getIdade());
            pet.setDescricao(petDTO.getDescricao());

            if (petDTO.getUsuarioId() != null) {
                Usuario usuario = usuarioService.buscarPorId(petDTO.getUsuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                pet.setUsuario(usuario);
            }

            if (petDTO.getFotos() != null) {
                List<FotoPet> novasFotos = petDTO.getFotos().stream().map(fotoDTO -> {
                    FotoPet foto = new FotoPet();
                    foto.setImagemBase64(fotoDTO.getImagemBase64());
                    foto.setPet(pet);
                    return foto;
                }).collect(Collectors.toList());
                pet.getFotos().addAll(novasFotos);
            }

            Pet atualizado = petService.salvar(pet);
            PetDTO dto = converterParaDTO(atualizado);
            return ResponseEntity.ok(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deletar(@PathVariable Long id) {
        Response<String> response = new Response<>();
        try {
            petService.deletar(id);
            response.setData("Pet removido com sucesso.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.getErrors().add("Erro ao deletar pet: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    // ------------------------------------------------------------ Metodo Privado ------------------------------------------------------------


    private PetDTO converterParaDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setNome(pet.getNome());
        dto.setRaca(pet.getRaca());
        dto.setPorte(pet.getPorte());
        dto.setSexo(pet.getSexo());
        dto.setIdade(pet.getIdade());
        dto.setDescricao(pet.getDescricao());

        if (pet.getUsuario() != null) {
            dto.setUsuarioId(pet.getUsuario().getId());
        }

        if (pet.getUsuario() != null && pet.getUsuario().getContato() != null) {
            dto.setWhatsapp(pet.getUsuario().getContato().getWhatsapp());
        }

        if (pet.getFotos() != null) {
            List<FotoPetDTO> fotosDTO = pet.getFotos().stream().map(foto -> {
                FotoPetDTO fotoDTO = new FotoPetDTO();
                fotoDTO.setId(foto.getId());
                fotoDTO.setImagemBase64(foto.getImagemBase64());
                return fotoDTO;
            }).collect(Collectors.toList());
            dto.setFotos(fotosDTO);
        }

        return dto;
    }
}
