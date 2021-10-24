package com.danilorocha.logistica.api.controller;

import com.danilorocha.logistica.domain.entity.Cliente;
import com.danilorocha.logistica.domain.repository.ClienteRepository;
import com.danilorocha.logistica.domain.service.CadastroClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private CadastroClienteService clienteService;
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }//listar todos clientes

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> cliente(@PathVariable Long id) {//PathVariable faz um bind do parametro passado na url para o parametro do metodo
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        /*Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.getById(id));
        if (cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        return ResponseEntity.notFound().build();*/
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {//RequestBody descerelializa o json da requisição em objeto java
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {//Valid faz a validação da entrega dos dados, conforme a anotação no parâmetro na Entidade
        //return ResponseEntity.ok(clienteRepository.saveAndFlush(cliente));
        if (!clienteRepository.existsById(id))
            return ResponseEntity.notFound().build();
        cliente.setId(id);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!clienteRepository.existsById(id))
            return ResponseEntity.notFound().build();
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}//classe
