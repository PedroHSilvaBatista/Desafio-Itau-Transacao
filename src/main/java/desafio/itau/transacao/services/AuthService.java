package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.LoginRequest;
import desafio.itau.transacao.dtos.LoginResponse;
import desafio.itau.transacao.entities.Usuario;
import desafio.itau.transacao.exceptions.RecursoNaoEncontradoException;
import desafio.itau.transacao.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOptional = usuarioService.procurarUsuarioPorEmail(loginRequest.email());

        if (usuarioOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado. Verifique as credenciais inseridas e tente novamente");
        }

        Usuario usuario = usuarioOptional.get();
        if (!passwordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
            throw new RecursoNaoEncontradoException("Credenciais inválidas. Verifique as credenciais inseridas e tente novamente");
        }

        String token = jwtService.gerarToken(usuario.getEmail(), usuario.getRole());

        return new LoginResponse(token);
    }
}
