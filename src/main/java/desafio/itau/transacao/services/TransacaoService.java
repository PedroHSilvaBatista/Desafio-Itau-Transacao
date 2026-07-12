package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.dtos.TransacaoRequest;
import desafio.itau.transacao.dtos.TransacaoResponse;
import desafio.itau.transacao.entities.Transacao;
import desafio.itau.transacao.entities.Usuario;
import desafio.itau.transacao.mappers.EstatisticaMapper;
import desafio.itau.transacao.mappers.TransacaoMapper;
import desafio.itau.transacao.repositories.TransacaoRepository;
import desafio.itau.transacao.utils.CalculadoraEstatistica;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransacaoService {
    @Autowired
    private TransacaoRepository repository;

    public TransacaoResponse salvarTransacao(TransacaoRequest request) {
        Transacao transacao = TransacaoMapper.toEntity(request);
        log.info("Request transformado com sucesso para entidade: {}", transacao);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transacao.setUsuario(usuario);
        repository.save(transacao);
        return TransacaoMapper.toResponse(transacao);
    }

    public void apagarTodasTransacoes() {
        repository.deleteAll();
    }

    public EstatisticaResponse calcularEstatisticas() {
        List<Transacao> transacoes = repository.findAll(); // Utilzar Redis depois
        return EstatisticaMapper.toResponse(CalculadoraEstatistica.calcularEstatisticas(transacoes));
    }
}
