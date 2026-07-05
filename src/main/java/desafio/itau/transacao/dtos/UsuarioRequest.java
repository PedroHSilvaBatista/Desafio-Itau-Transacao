package desafio.itau.transacao.dtos;

import desafio.itau.transacao.entities.enums.Role;
import jakarta.validation.constraints.*;

public record UsuarioRequest(@NotBlank(message = "O campo precisa ser preenchido") String nomeCompleto,
                             @NotBlank(message = "O campo precisa ser preenchido") @Email String email,
                             @NotBlank(message = "O campo precisa ser preenchido") String telefone,
                             @NotBlank(message = "O campo precisa ser preenchido") @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00") @Size(min = 14, max = 14, message = "CPF deve estar no formato 123.456.789-09") String cpf,
                             @NotBlank(message = "O campo precisa ser preenchido") String senha,
                             @NotNull(message = "O campo precisa ser preenchido") Role role) {
}
