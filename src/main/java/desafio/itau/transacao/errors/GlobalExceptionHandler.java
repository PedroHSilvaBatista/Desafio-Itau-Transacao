package desafio.itau.transacao.errors;

import desafio.itau.transacao.exceptions.CPFValidacaoException;
import desafio.itau.transacao.exceptions.EmailValidacaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> fieldError = new HashMap<>();
                    fieldError.put("campo", error.getField());
                    fieldError.put("mensagem", error.getDefaultMessage());
                    return fieldError;
                }).toList();


        log.error("ERRO: Erro de validação na passagem dos campos: {}", fieldErrors);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Erro de validação");
        pd.setDetail("Um ou mais campos estão inválidos");
        pd.setProperty("Timestamp", OffsetDateTime.now());
        pd.setProperty("Details", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("ERRO: Erro de estrutura do payload");

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Erro de estrutura do payload");
        pd.setProperty("Timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception ex) {
        log.error("ERRO: Erro genérico");

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        pd.setTitle("Erro interno no servidor");
        pd.setProperty("Timestamp",OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
    }

    @ExceptionHandler(CPFValidacaoException.class)
    public ResponseEntity<ProblemDetail> handleValidacaoCPF(CPFValidacaoException ex) {
        log.error("ERRO: CPF já encontrado na base dados");

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("O CPF inserido já foi cadastrado");
        pd.setProperty("Timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(EmailValidacaoException.class)
    public ResponseEntity<ProblemDetail> handleValidacaoEmail(EmailValidacaoException ex) {
        log.error("ERRO: Email já encontrado na base dados");

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("O Email inserido já foi cadastrado");
        pd.setProperty("Timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }
}
