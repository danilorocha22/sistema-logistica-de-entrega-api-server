package com.danilorocha.logistica.api.map;

import com.danilorocha.logistica.api.model.output.OcorrenciaOutput;
import com.danilorocha.logistica.domain.entity.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMap {

    private ModelMapper modelMapper;

    public OcorrenciaOutput toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaOutput.class);
    }

    public List<OcorrenciaOutput> toListModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
    }

}
