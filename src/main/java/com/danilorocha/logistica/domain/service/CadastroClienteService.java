package com.danilorocha.logistica.domain.service;

import com.danilorocha.logistica.domain.entity.Cliente;
import com.danilorocha.logistica.domain.exception.NegocioException;
import com.danilorocha.logistica.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {//Torna essa classe um serviço para executar regras de negócio

    private ClienteRepository clienteRepository;

    @Transactional//declara que o metodo deve ser executado dentro de uma transação e ser der errado tudo que está na transação será descartado
    public Cliente salvar(Cliente cliente) {
        boolean emailExistente =  clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExiste -> !clienteExiste.equals(cliente));
        if (emailExistente) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

}//classe
