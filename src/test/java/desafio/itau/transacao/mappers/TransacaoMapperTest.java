package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.entities.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;


class TransacaoMapperTest {

    @Test
    void deveriaRetornarUmaEntidadeTransacao() {
        // ARRANGE
        TransacaoRequest transacaoRequest = new TransacaoRequest(200, OffsetDateTime.parse("2026-05-24T15:30:45.123456789-03:00"));

        // ACT
        Transacao transacao = TransacaoMapper.toEntity(transacaoRequest);

        // ASSERT
        Assertions.assertEquals(200, transacao.getValor());
        Assertions.assertEquals(transacaoRequest.dataHora(), transacao.getDataHora());
    }

}