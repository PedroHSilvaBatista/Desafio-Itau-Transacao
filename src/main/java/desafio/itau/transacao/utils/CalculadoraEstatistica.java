package desafio.itau.transacao.utils;

import desafio.itau.transacao.entities.Estatistica;
import desafio.itau.transacao.entities.Transacao;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class CalculadoraEstatistica {

    public static Estatistica calcularEstatisticas(List<Transacao> transacoes) {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(300);

        DoubleSummaryStatistics stats = transacoes
                .stream()
                .filter(t -> !t.getDataHora().isBefore(limite) &&
                        !t.getDataHora().isAfter(agora))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        return new Estatistica(stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getCount() == 0 ? 0 : stats.getMin(),
                stats.getCount() == 0 ? 0 : stats.getMax());
    }
}
