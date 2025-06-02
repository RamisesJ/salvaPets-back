package com.backend.salvapets.domain.service;

import com.backend.salvapets.domain.model.Usuario;
import com.backend.salvapets.domain.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
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


    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}