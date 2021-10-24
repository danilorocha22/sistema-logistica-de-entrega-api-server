package com.danilorocha.logistica.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//Esta anotação define esta classe como componente do Spring, com objetivo de configuração de beans
public class ModelMapperConfig {

    @Bean//Define o método para retornar um bean gerenciado pelo Spring
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
