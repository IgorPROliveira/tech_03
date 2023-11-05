package com.techchallenge.parquimetro.dominios.estacionamento.model;

import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;
import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
@Document
public class Estacionamento {

    @Id
    private String id;
    private LocalDateTime dtEntrada;
    private LocalDateTime dtSaida;
    private PeriodoEstacionamento periodoEstacionamento;
    private FormaPagamento formaPagamento;
    private BigDecimal valor;
    @DBRef
    private Veiculo veiculo;
    @DBRef
    private Condutor condutor;



}
