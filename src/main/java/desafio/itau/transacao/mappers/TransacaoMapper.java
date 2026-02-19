package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.entities.Transacao;

public class TransacaoMapper {
    public static Transacao toEntity(TransacaoRequest request) {
        return Transacao.builder()
                .valor(request.valor())
                .dataHora(request.dataHora())
                .build();
    }
}
