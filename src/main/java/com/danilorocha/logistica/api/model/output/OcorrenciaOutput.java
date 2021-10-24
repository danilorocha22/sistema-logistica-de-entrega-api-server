package com.danilorocha.logistica.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaOutput {

    private Long id;
    private String descricao;
    private OffsetDateTime dataOcorrencia;
}
