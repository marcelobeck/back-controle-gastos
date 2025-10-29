package mapper;
import dtos.RegistroDto;
import dtos.UsuarioDto;
import models.Usuario;
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
