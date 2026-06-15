package desafio.itau.transacao.controllers;


import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.errors.GlobalExceptionHandler;
import desafio.itau.transacao.services.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {
    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRequest request;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void deveriaRetornarErro422ParaSalvarTransacao() throws Exception {
        // ARRANGE
        String json = """
                {
                  "valor": 123.45
                }
                """;

        // ACT
        transacaoController.salvarTransacao(request);

        // ASSERT
        Mockito.verify(transacaoService).salvarTransacao(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    void deveriaRetornarStatusCode200ParaSalvarTransacao() throws Exception {
        // ARRANGE
        String json = """
                {
                  "valor": 123.45,
                  "dataHora": "2026-01-07T19:04:00.789-03:00"
                }
                """;

        // ACT
        transacaoController.salvarTransacao(request);

        // ASSERT
        Mockito.verify(transacaoService).salvarTransacao(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void deveriaRetornarStatusCode200ParaApagarTransacoes() throws Exception {
        // ACT
        transacaoController.apagarTodasTransacoes();

        // ASSERT
        Mockito.verify(transacaoService).apagarTodasTransacoes();
        mockMvc.perform(MockMvcRequestBuilders.delete("/transacao"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deveriaRetornarCodigo200ParaExibirEstatisticas() throws Exception {
        // ACT
        transacaoController.calcularEstatisticas();

        // ASSERT
        Mockito.verify(transacaoService).calcularEstatisticas();
        mockMvc.perform(MockMvcRequestBuilders.get("/transacao/estatistica"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}