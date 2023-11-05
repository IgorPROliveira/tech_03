package com.techchallenge.parquimetro.dominios.veiculo.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Veiculo {

    @Id
    @EqualsAndHashCode.Include
    private String placa;
    private String modelo;


}
