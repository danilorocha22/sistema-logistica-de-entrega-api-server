package com.danilorocha.logistica.domain.entity;

import com.danilorocha.logistica.domain.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;

@Data
@Entity //identifica a tabela no BD
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @Id // identifica a chave primária na tabela
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)//estratégia de geração da chave, que nesse caso será auto_increment
    private Long id;

    //@NotNull não nulo
    @NotBlank//Não nulo e não vazio
    @Size(max = 60)//tamanho máximo do nome igual no BD
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email //Validar se a sintaxe do e-mail está correta
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "fone")
    private String telefone;

}
