package com.backend.salvapets.api.controllers;

import com.backend.salvapets.api.dto.LoginRequestDTO;
import com.backend.salvapets.api.dto.ResponseDTO;
import com.backend.salvapets.api.dto.UsuarioDTO;
import com.backend.salvapets.domain.model.Perfil;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.repositories.UsuarioRepository;
import com.backend.salvapets.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenService tokenService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario user = this.usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.gerarToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body){
        Optional<Usuario> user = this.usuarioRepository.findByEmail(body.getEmail());

        if(user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setNome(body.getNome());
            newUser.setCpf(body.getCpf());
            newUser.setEmail(body.getEmail());
            newUser.setStatus("1");
            newUser.setPerfil(Perfil.COMUM);
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setContato(body.getContato());
            this.usuarioRepository.save(newUser);

            String token = this.tokenService.gerarToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));

        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
