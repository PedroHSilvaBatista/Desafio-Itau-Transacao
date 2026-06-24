package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.entities.Transacao;
import desafio.itau.transacao.mappers.EstatisticaMapper;
import desafio.itau.transacao.mappers.TransacaoMapper;
import desafio.itau.transacao.repositories.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransacaoService {
    @Autowired
    private TransacaoRepository repository;

    public void salvarTransacao(TransacaoRequest request) {
        Transacao transacao = TransacaoMapper.toEntity(request);
        log.info("Request transformado com sucesso para entidade: {}", transacao);
        repository.salvarTransacao(transacao);
    }

    public void apagarTodasTransacoes() {
        repository.apagarTransacoes();
    }

    public EstatisticaResponse calcularEstatisticas() {
        return EstatisticaMapper.toResponse(repository.calcularEstatisticas());
    }
}
