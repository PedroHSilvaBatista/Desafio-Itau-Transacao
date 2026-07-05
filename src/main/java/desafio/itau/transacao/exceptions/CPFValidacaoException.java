package desafio.itau.transacao.exceptions;

public class CPFValidacaoException extends RuntimeException {
    public CPFValidacaoException(String mensagem) {
        super(mensagem);
    }
}
