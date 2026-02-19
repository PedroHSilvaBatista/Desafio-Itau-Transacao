package desafio.itau.transacao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class Transacao {
    private double valor;
    private OffsetDateTime dataHora;
}
