package com.danilorocha.logistica.api.controller;

import com.danilorocha.logistica.api.map.EntregaMap;
import com.danilorocha.logistica.api.model.input.EntregaInput;
import com.danilorocha.logistica.api.model.output.EntregaOutput;
import com.danilorocha.logistica.domain.entity.Entrega;
import com.danilorocha.logistica.domain.repository.EntregaRepository;
import com.danilorocha.logistica.domain.service.FinalizacaoEntregaService;
import com.danilorocha.logistica.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private FinalizacaoEntregaService finalizacaoService;
    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService entregaService;
    private EntregaMap entregaMap;

    @GetMapping
    public List<EntregaOutput> listar() {
        return entregaMap.toListModel(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaOutput> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMap.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaOutput solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaMap.toEntity(entregaInput);
        Entrega entregaSolicitada = entregaService.solicitar(novaEntrega);
        return entregaMap.toModel(entregaSolicitada);
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        finalizacaoService.finalizar(id);
    }

}//classe
