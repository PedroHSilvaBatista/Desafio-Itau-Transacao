package desafio.itau.transacao.repositories;

import desafio.itau.transacao.entities.Transacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    private final List<Transacao> transacoes = new ArrayList<>();

    // POST
    public void salvarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        System.out.println(transacoes);
    }

    // DELETE
    public void apagarTransacoes() {
        transacoes.clear();
        System.out.println(transacoes);
    }

    // Implementar o GET
}
