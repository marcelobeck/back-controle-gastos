package com.example.controle_gastos_api.dtos;

import java.time.LocalDateTime;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
){

}
