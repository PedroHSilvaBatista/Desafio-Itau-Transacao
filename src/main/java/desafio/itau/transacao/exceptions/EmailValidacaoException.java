package desafio.itau.transacao.exceptions;

public class EmailValidacaoException extends RuntimeException {
    public EmailValidacaoException(String mensagem) {
        super(mensagem);
    }
}
