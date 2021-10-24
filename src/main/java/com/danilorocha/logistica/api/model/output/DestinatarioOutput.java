package com.danilorocha.logistica.api.model.output;

import com.danilorocha.logistica.domain.entity.Destinatario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioOutput {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}
