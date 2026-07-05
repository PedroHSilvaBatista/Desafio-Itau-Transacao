package desafio.itau.transacao.entities;

import desafio.itau.transacao.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<Transacao> transacao;

    private boolean ativo = true;

    public Usuario () {

    }

    public Usuario (String nomeCompleto, String email, String telefone, String cpf, String senha, Role role) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
        this.role = role;
    }

}
