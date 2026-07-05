package desafio.itau.transacao.controllers;

import desafio.itau.transacao.dtos.UsuarioRequest;
import desafio.itau.transacao.dtos.UsuarioResponse;
import desafio.itau.transacao.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v2/usuarios")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder uriBuilder) {
        log.info("Salvando transação com o body: {}", usuarioRequest);
        UsuarioResponse response = usuarioService.cadastrarUsuario(usuarioRequest);
        log.info("Usuário {} cadastrado com sucesso!", response.nomeCompleto());
        var uri = uriBuilder.path("/api/v2/usuarios/{}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
