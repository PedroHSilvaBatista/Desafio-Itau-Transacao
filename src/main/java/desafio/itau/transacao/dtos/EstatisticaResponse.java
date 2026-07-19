package desafio.itau.transacao.dtos;

import java.io.Serializable;

public record EstatisticaResponse (long count,
                                   double sum,
                                   double avg,
                                   double min,
                                   double max) implements Serializable {
}
