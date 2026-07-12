package desafio.itau.transacao.services;

import desafio.itau.transacao.dtos.UsuarioRequest;
import desafio.itau.transacao.dtos.UsuarioResponse;
import desafio.itau.transacao.entities.Usuario;
import desafio.itau.transacao.exceptions.CPFValidacaoException;
import desafio.itau.transacao.exceptions.EmailValidacaoException;
import desafio.itau.transacao.mappers.UsuarioMapper;
import desafio.itau.transacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse cadastrarUsuario(UsuarioRequest request) {

        // Verifica se já existe uma pessoa cadastrada com o mesmo CPF
        if (usuarioRepository.existsByCpf(request.cpf())) {
            throw new CPFValidacaoException("Já existe uma pessoa com o mesmo CPF cadastrado");
        } else if (usuarioRepository.existsByEmail(request.email())) {
            // Verifica se já existe uma pessoa cadastrada com o mesmo e-mail
            throw new EmailValidacaoException("Já existe uma pessoa com o mesmo Email cadastrado");
        }

        // Converte o request para entidade, define o login e persiste no banco
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }

    public Optional<Usuario> procurarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
