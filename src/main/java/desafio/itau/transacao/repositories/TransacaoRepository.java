package desafio.itau.transacao.repositories;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.entities.Estatistica;
import desafio.itau.transacao.entities.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Repository
public class TransacaoRepository {
    private final List<Transacao> transacoes = new ArrayList<>();

    // POST
    public void salvarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        System.out.println(transacoes);
    }

    // DELETE
    public void apagarTransacoes() {
        transacoes.clear();
        System.out.println(transacoes);
    }

    // Implementar o GET
    public Estatistica calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(60);

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
