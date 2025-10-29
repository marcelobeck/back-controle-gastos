package controllers;

import dtos.LoginDto;
import dtos.RegistroDto;
import dtos.UsuarioDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mapper.UsuarioMapper;
import models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> cadastrar(@Valid @RequestBody RegistroDto dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario salvo = usuarioService.salvar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioMapper.toDto(salvo));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@Valid @RequestBody LoginDto dto, HttpSession session) {
        return usuarioService.buscarPorEmail(dto.email())
                .filter(usuario -> usuarioService.verificarSenha(usuario, dto.senha()))
                .map(usuario -> {
                    session.setAttribute("usuarioLogado", usuario);
                    return ResponseEntity.ok(usuarioMapper.toDto(usuario));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDto> usuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(usuarioMapper.toDto(usuario));
    }

}
