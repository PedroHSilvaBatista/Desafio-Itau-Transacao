package desafio.itau.transacao.entities;

import desafio.itau.transacao.entities.enums.StatusOperacao;
import desafio.itau.transacao.entities.enums.TipoOperacao;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Getter
@Setter
@Document(collection = "logs_auditoria")
public class LogAuditoria {
    @Id
    private String id;
    private TipoOperacao tipoOperacao;
    private Double valorOperacao;
    private OffsetDateTime timeStamp;
    private String emailUsuario;
    private StatusOperacao statusOperacao;
    private String enderecoIp;
}
