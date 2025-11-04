package com.example.controle_gastos_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.controle_gastos_api.models.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.controle_gastos_api.repositories.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean verificarSenha(Usuario usuario, String senhaInformada) {
        return passwordEncoder.matches(senhaInformada, usuario.getSenha());
    }
}
