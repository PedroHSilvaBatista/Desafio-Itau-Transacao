package desafio.itau.transacao.dtos;

public record UsuarioResponse(Long id,
                              String nomeCompleto,
                              String email,
                              String senha) {
}
