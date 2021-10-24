package com.danilorocha.logistica.api.exceptionhandler;

import com.danilorocha.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.danilorocha.logistica.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice//Identifica esta classe com propósito de tratar exceções globalmente, ou seja, para todos os Controllers da aplicação
public class ExcecaoHandler extends ResponseEntityExceptionHandler {//ResponseEntityExceptionHandler classe base para tratar várias exceções

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Excecao.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            //String mensagem = error.getDefaultMessage();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Excecao.Campo(nome, mensagem));
        }

        Excecao excecao = new Excecao();
        excecao.setStatus(status.value());
        excecao.setDataHora(OffsetDateTime.now());
        excecao.setTitulo("Um ou mais campos são inválidos. Preencha os campos corretamente e tente novamente.");
        excecao.setCampo(campos);
        return handleExceptionInternal(ex, excecao, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Excecao excecao = new Excecao();
        excecao.setStatus(httpStatus.value());
        excecao.setDataHora(OffsetDateTime.now());
        excecao.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, excecao, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Excecao excecao = new Excecao();
        excecao.setStatus(httpStatus.value());
        excecao.setDataHora(OffsetDateTime.now());
        excecao.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, excecao, new HttpHeaders(), httpStatus, request);
    }

}//classe
