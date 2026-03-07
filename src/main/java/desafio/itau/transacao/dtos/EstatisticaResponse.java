package desafio.itau.transacao.dtos;

public record EstatisticaResponse(long count,
                                  double sum,
                                  double avg,
                                  double min,
                                  double max) {
}
