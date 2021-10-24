package com.danilorocha.logistica.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatrioInput destinatario;

    @NotNull
    private BigDecimal taxa;

}
