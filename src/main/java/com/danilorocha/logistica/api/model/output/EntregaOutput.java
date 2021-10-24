package com.danilorocha.logistica.api.model.output;

import com.danilorocha.logistica.domain.entity.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class EntregaOutput {

    private Long id;
    private ClienteResumoOutput cliente;
    private DestinatarioOutput destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
