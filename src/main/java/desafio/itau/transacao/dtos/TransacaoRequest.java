package desafio.itau.transacao.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequest (double valor,
                                OffsetDateTime dataHora) {

}
