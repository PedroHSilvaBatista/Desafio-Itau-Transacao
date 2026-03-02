package desafio.itau.transacao.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.OffsetDateTime;

@JsonIgnoreProperties()
public record TransacaoRequest (@NotNull @Min(value = 0) double valor,
                                @NotNull @Past OffsetDateTime dataHora) {

}
