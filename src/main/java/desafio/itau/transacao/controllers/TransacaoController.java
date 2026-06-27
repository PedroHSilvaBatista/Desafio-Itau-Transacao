package desafio.itau.transacao.controllers;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.dtos.TransacaoResponse;
import desafio.itau.transacao.services.TransacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v2/transacao")
@Slf4j
public class TransacaoController {
    @Autowired
    private TransacaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<TransacaoResponse> salvarTransacao(@RequestBody @Valid TransacaoRequest body, UriComponentsBuilder uriBuilder) {
        log.info("Salvando transação com o body: {}", body);
        TransacaoResponse response = service.salvarTransacao(body);
        var uri = uriBuilder.path("/api/v2/transacao/{id}").buildAndExpand(response.id()).toUri();
        log.info("Transação salva com sucesso!");
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> apagarTodasTransacoes() {
        service.apagarTodasTransacoes();
        log.info("Transações apagadas com sucesso!");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponse> calcularEstatisticas() {
        EstatisticaResponse estatisticas = service.calcularEstatisticas();
        log.info("Carregamento das estatísticas realizado com sucesso!");
        return ResponseEntity.ok(estatisticas);
    }
}
