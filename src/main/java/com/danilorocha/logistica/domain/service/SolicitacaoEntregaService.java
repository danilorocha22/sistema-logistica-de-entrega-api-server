package com.danilorocha.logistica.domain.service;

import com.danilorocha.logistica.domain.entity.Cliente;
import com.danilorocha.logistica.domain.entity.Entrega;
import com.danilorocha.logistica.domain.entity.StatusEntrega;
import com.danilorocha.logistica.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CadastroClienteService clienteService;
    private EntregaRepository entregaRepository;

    public Entrega solicitar(Entrega entrega) {
        //TODO implementar todas regras de negócio deste caso de uso aqui
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

}
