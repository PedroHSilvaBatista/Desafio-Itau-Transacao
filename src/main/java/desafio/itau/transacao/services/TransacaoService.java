package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.dtos.TransacaoResponse;
import desafio.itau.transacao.entities.Transacao;
import desafio.itau.transacao.mappers.TransacaoMapper;
import desafio.itau.transacao.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository repository;

    public TransacaoResponse salvarTransacao(TransacaoRequest request) {
        Transacao transacao = TransacaoMapper.toEntity(request);
        repository.salvarTransacao(transacao);
        return null;
    }
}
