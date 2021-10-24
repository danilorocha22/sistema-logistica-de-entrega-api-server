package com.danilorocha.logistica.api.controller;

import com.danilorocha.logistica.api.map.OcorrenciaMap;
import com.danilorocha.logistica.api.model.input.OcorrenciaInput;
import com.danilorocha.logistica.api.model.output.OcorrenciaOutput;
import com.danilorocha.logistica.domain.entity.Entrega;
import com.danilorocha.logistica.domain.entity.Ocorrencia;
import com.danilorocha.logistica.domain.service.BuscaEntregaService;
import com.danilorocha.logistica.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService entregaService;
    private RegistroOcorrenciaService ocorrenciaService;
    private OcorrenciaMap ocorrenciaMap;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaOutput registrar(@PathVariable Long id,
                                      @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada =  ocorrenciaService.registrar(
                id, ocorrenciaInput.getDescricao());
        return ocorrenciaMap.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaOutput> listar(@PathVariable Long id) {
        Entrega entrega = entregaService.buscar(id);
        return ocorrenciaMap.toListModel(entrega.getOcorrencias());
    }
}
