package com.example.controle_gastos_api.mapper;
import com.example.controle_gastos_api.dtos.RegistroDto;
import com.example.controle_gastos_api.dtos.UsuarioDto;
import com.example.controle_gastos_api.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(Usuario entity);

    Usuario toEntity(RegistroDto dto);

    /*
     * Conversão de DTO <-> Entity
     *
     * toEntity() deve ser usado quando:
     *   - Criar (POST)
     *   - Atualizar (PUT/PATCH)
     *   - Qualquer operação que envolva persistência no banco
     *
     * toDto() deve ser usado quando:
     *   - Retornar dados (GET)
     *   - Confirmar criação/atualização
     *   - Qualquer resposta enviada para o cliente
     *
     */
}
