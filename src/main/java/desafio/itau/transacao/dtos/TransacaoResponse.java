package desafio.itau.transacao.dtos;

import desafio.itau.transacao.entities.Transacao;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record TransacaoResponse(Long id,
                                Double valor,
                                OffsetDateTime dataHora) {
    public TransacaoResponse(Transacao transacao) {
        this(transacao.getId(), transacao.getValor(), transacao.getDataHora());
    }
}
