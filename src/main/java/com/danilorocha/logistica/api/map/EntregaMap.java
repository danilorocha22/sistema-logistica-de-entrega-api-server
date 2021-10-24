package com.danilorocha.logistica.api.map;

import com.danilorocha.logistica.api.model.input.EntregaInput;
import com.danilorocha.logistica.api.model.output.EntregaOutput;
import com.danilorocha.logistica.domain.entity.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component//Define esta classe como um componente do Spring
public class EntregaMap {

    private ModelMapper modelMapper;
    
    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

    public EntregaOutput toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaOutput.class);
    }

    public List<EntregaOutput> toListModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
}
