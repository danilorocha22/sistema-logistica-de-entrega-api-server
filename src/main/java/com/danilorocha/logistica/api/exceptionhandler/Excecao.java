package com.danilorocha.logistica.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)//Não permite visualizar no Json propriedades nulas
public class Excecao {
    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campo> campo;

    @AllArgsConstructor
    @Getter
    public static class Campo {
        private String nome;
        private String mensagem;
    }//classe interna

}//classe
