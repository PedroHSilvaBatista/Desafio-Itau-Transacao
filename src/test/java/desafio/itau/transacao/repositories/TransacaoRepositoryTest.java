package desafio.itau.transacao.repositories;

import desafio.itau.transacao.entities.Estatistica;
import desafio.itau.transacao.entities.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoRepositoryTest {
    @Test
    void deveriaSalvarTransacao() {
        // ARRANGE
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao = new Transacao(200, OffsetDateTime.now());

        // ACT
        transacaoRepository.salvarTransacao(transacao);
        Estatistica estatisticaMetodo = transacaoRepository.calcularEstatisticas();

        // ASSERT
        Assertions.assertEquals(1, estatisticaMetodo.getCount());
    }

    @Test
    void deveriaApagarTransacao() {
        // ARRANGE
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao = new Transacao(200, OffsetDateTime.now());

        // ACT
        transacaoRepository.salvarTransacao(transacao);
        transacaoRepository.apagarTransacoes();
        Estatistica estatistica = transacaoRepository.calcularEstatisticas();

        // ASSERT
        Assertions.assertEquals(0, estatistica.getCount());
    }

    @Test
    void deveriaRetornarEstatistica() {
        // ARRANGE
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao = new Transacao(200, OffsetDateTime.now());

        // ACT - Sugestão: Separar cada cenário em um teste independente
        transacaoRepository.salvarTransacao(transacao);
        Estatistica estatistica = transacaoRepository.calcularEstatisticas();

        // ASSERT
        Assertions.assertEquals(1, estatistica.getCount());
        Assertions.assertEquals(200, estatistica.getSum());
        Assertions.assertEquals(200, estatistica.getAvg());
        Assertions.assertEquals(200, estatistica.getMax());
        Assertions.assertEquals(200, estatistica.getMin());
    }

    @Test
    void naoDeveriaRetornarEstatisticasParaTransacoesRemovidas() {
        // ARRANGE
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao = new Transacao(200, OffsetDateTime.now());

        // ACT
        transacaoRepository.salvarTransacao(transacao);
        transacaoRepository.apagarTransacoes();
        Estatistica estatistica = transacaoRepository.calcularEstatisticas();

        // ASSERT
        Assertions.assertEquals(0, estatistica.getCount());
        Assertions.assertEquals(0, estatistica.getSum());
        Assertions.assertEquals(0, estatistica.getAvg());
        Assertions.assertEquals(0, estatistica.getMax());
        Assertions.assertEquals(0, estatistica.getMin());
    }

    @Test
    void naoDeveriaRetornarEstatisticasParaTransacoesPassadas() {
        // ARRANGE
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacaoForaDoIntervalo = new Transacao(100, OffsetDateTime.parse("2026-05-24T15:30:45.123456789-03:00"));

        // ACT
        transacaoRepository.salvarTransacao(transacaoForaDoIntervalo);
        Estatistica estatistica = transacaoRepository.calcularEstatisticas();

        // ASSERT
        Assertions.assertEquals(0, estatistica.getCount());
        Assertions.assertEquals(0, estatistica.getSum());
        Assertions.assertEquals(0, estatistica.getAvg());
        Assertions.assertEquals(0, estatistica.getMax());
        Assertions.assertEquals(0, estatistica.getMin());
    }
}