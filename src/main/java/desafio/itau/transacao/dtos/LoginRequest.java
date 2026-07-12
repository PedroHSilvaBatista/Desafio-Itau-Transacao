package desafio.itau.transacao.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (@NotBlank (message = "O campo email precisa ser preenchido") String email,
                           @NotBlank(message = "O campo senha precisa ser preenchido") String senha) {
}
