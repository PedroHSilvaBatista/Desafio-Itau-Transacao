package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.entities.Estatistica;

public class EstatisticaMapper {

    public static EstatisticaResponse toResponse(Estatistica estatistica) {
        return new EstatisticaResponse(estatistica.getCount(),
                estatistica.getSum(),
                estatistica.getAvg(),
                estatistica.getMin(),
                estatistica.getMax());
    }
}
