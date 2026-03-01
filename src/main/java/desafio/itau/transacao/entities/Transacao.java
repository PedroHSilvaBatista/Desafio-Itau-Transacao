package desafio.itau.transacao.entities;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Transacao {
    private double valor;
    private OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "{Valor: " +  valor  + "; dataHora: " + dataHora +  "}";
    }
}
