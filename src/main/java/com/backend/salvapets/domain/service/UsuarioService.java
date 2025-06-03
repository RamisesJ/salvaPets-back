package com.backend.salvapets.domain.service;

import com.backend.salvapets.api.dto.AlterarSenhaDTO;
import com.backend.salvapets.api.dto.UsuarioDTO;
import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario novoUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(novoUsuario.getNome());
        usuarioExistente.setCpf(novoUsuario.getCpf());
        usuarioExistente.setEmail(novoUsuario.getEmail());
        usuarioExistente.setPassword(novoUsuario.getPassword());
        usuarioExistente.setContato(novoUsuario.getContato());

        return usuarioRepository.save(usuarioExistente);
    }

    public void atualizarSenha(Long id, AlterarSenhaDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getPassword())) {
            throw new RuntimeException("Senha atual incorreta.");
        }

        usuario.setPassword(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setCpf(dto.getCpf());
        usuarioExistente.setEmail(dto.getEmail());

        if (dto.getContato() != null) {
            usuarioExistente.setContato(dto.getContato()); // Pode ser substituído por update campo a campo se necessário
        }
        return usuarioRepository.save(usuarioExistente);
    }


    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}