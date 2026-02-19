package desafio.itau.transacao.repositories;

import desafio.itau.transacao.entities.Transacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransacaoRepository {
    private List<Transacao> transacoes;

    // POST
    public void salvarTransacao(Transacao transacao, List<Transacao> transacoes) {

    }

    // DELETE
    public void apagarTransacoes(List<Transacao> transacoes) {

    }

    // Implementar o GET
}
