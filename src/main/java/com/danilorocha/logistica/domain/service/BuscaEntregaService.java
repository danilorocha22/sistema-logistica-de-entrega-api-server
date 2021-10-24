package com.danilorocha.logistica.domain.service;

import com.danilorocha.logistica.domain.entity.Entrega;
import com.danilorocha.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.danilorocha.logistica.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega nao encontrada"));
    }
}
