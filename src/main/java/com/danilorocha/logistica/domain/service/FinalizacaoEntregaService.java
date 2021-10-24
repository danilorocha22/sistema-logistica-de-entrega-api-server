package com.danilorocha.logistica.domain.service;

import com.danilorocha.logistica.domain.entity.Entrega;
import com.danilorocha.logistica.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService entregaService;

    @Transactional
    public void finalizar(Long id) {
        Entrega entrega = entregaService.buscar(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}
