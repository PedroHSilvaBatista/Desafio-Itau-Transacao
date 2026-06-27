package desafio.itau.transacao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;
    private OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Transacao() {

    }

    @Override
    public String toString() {
        return "{Valor: " +  valor  + "; dataHora: " + dataHora +  "}";
    }
}
