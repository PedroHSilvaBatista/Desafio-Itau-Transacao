package desafio.itau.transacao.controllers;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity salvarTransacao(@RequestBody @Valid TransacaoRequest body) {
        service.salvarTransacao(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity apagarTodasTransacoes() {
        service.apagarTodasTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponse> calcularEstatisticas() {
        EstatisticaResponse estatisticas = service.calcularEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }
}
