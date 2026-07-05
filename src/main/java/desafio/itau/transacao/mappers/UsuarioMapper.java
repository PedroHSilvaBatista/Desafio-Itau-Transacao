package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.UsuarioRequest;
import desafio.itau.transacao.dtos.UsuarioResponse;
import desafio.itau.transacao.entities.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest usuarioRequest) {
        return new Usuario(usuarioRequest.nomeCompleto(),
                usuarioRequest.email(),
                usuarioRequest.telefone(),
                usuarioRequest.cpf(),
                usuarioRequest.senha(),
                usuarioRequest.role());
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getSenha());
    }
}
