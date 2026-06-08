package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.entities.Estatistica;
import desafio.itau.transacao.entities.Transacao;
import desafio.itau.transacao.mappers.EstatisticaMapper;
import desafio.itau.transacao.mappers.TransacaoMapper;
import desafio.itau.transacao.repositories.TransacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private Transacao transacao;

    @Mock
    private EstatisticaResponse estatisticaResponse;

    @Mock
    private Estatistica estatistica;

    private TransacaoRequest transacaoRequest;


    @Test
    void deveriaSalvarTransacao() {
        try (MockedStatic<TransacaoMapper> mocked = mockStatic(TransacaoMapper.class)) {
            // ARRANGE
            transacaoRequest = new TransacaoRequest(200, OffsetDateTime.parse("2026-05-24T15:30:45.123456789-03:00"));
            mocked.when(() -> TransacaoMapper.toEntity(any(TransacaoRequest.class))).thenReturn(transacao);

            // ACT
            transacaoService.salvarTransacao(transacaoRequest);


            // ASSERT
            mocked.verify(() -> TransacaoMapper.toEntity(any(TransacaoRequest.class)));
            Mockito.verify(transacaoRepository).salvarTransacao(transacao);
        }
    }

    @Test
    void deveriaApagarTransacoes() {
        // ACT
        transacaoService.apagarTodasTransacoes();

        // ASSERT
        Mockito.verify(transacaoRepository).apagarTransacoes();
    }

    @Test
    void deveriaCalcularEstatisticas() {
        try(MockedStatic<EstatisticaMapper> mocked = mockStatic(EstatisticaMapper.class)) {
            // ARRANGE
            Mockito.when(transacaoRepository.calcularEstatisticas()).thenReturn(estatistica);
            mocked.when(() -> EstatisticaMapper.toResponse(any(Estatistica.class))).thenReturn(estatisticaResponse);

            // ACT
            EstatisticaResponse response = transacaoService.calcularEstatisticas();

            // ASSERT
            Mockito.verify(transacaoRepository).calcularEstatisticas();
            mocked.verify(() -> EstatisticaMapper.toResponse(any(Estatistica.class)));
            Assertions.assertEquals(estatisticaResponse, response);
        }
    }
}