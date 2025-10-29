package dtos;

import java.time.LocalDateTime;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
){

}
