package com.backend.salvapets.api.controllers;

import com.backend.salvapets.api.dto.AlterarSenhaDTO;
import com.backend.salvapets.api.dto.LoginRequestDTO;
import com.backend.salvapets.api.dto.ResponseDTO;
import com.backend.salvapets.api.dto.UsuarioDTO;
import com.backend.salvapets.domain.model.Perfil;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.repositories.UsuarioRepository;
import com.backend.salvapets.domain.service.UsuarioService;
import com.backend.salvapets.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenService tokenService;

    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario user = this.usuarioService.buscarPorEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.gerarToken(user);
            return ResponseEntity.ok(new ResponseDTO( user.getId(), user.getNome(), user.getPerfil(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body){
        Optional<Usuario> user = this.usuarioService.buscarPorEmail(body.getEmail());

        if(user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setNome(body.getNome());
            newUser.setCpf(body.getCpf());
            newUser.setEmail(body.getEmail());
            newUser.setStatus("1");
            newUser.setPerfil(Perfil.COMUM);
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setContato(body.getContato());
            this.usuarioService.salvar(newUser);

            String token = this.tokenService.gerarToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getId(), newUser.getNome(), newUser.getPerfil(), token));

        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/usuarios/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody AlterarSenhaDTO dto) {
        try {
            usuarioService.atualizarSenha(id, dto);
            return ResponseEntity.ok("Senha atualizada com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
