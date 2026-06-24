package desafio.itau.transacao.controllers;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@Slf4j
public class TransacaoController {
    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity salvarTransacao(@RequestBody @Valid TransacaoRequest body) {
        log.info("Salvando transação com o body: {}", body);
        service.salvarTransacao(body);
        log.info("Transação salva com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity apagarTodasTransacoes() {
        service.apagarTodasTransacoes();
        log.info("Transações apagadas com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponse> calcularEstatisticas() {
        EstatisticaResponse estatisticas = service.calcularEstatisticas();
        log.info("Carregamento das estatísticas realizado com sucesso!");
        return ResponseEntity.ok(estatisticas);
    }
}
