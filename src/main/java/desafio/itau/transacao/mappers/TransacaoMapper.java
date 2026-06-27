package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.dtos.TransacaoResponse;
import desafio.itau.transacao.entities.Transacao;

public class TransacaoMapper {
    public static Transacao toEntity(TransacaoRequest request) {
        return new Transacao(request.valor(), request.dataHora());
    }

    public static TransacaoResponse toResponse(Transacao transacao) {
        return TransacaoResponse.builder()
                .id(transacao.getId())
                .valor(transacao.getValor())
                .dataHora(transacao.getDataHora())
                .build();
    }
}
