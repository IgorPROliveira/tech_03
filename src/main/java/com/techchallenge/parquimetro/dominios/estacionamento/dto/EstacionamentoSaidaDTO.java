package com.techchallenge.parquimetro.dominios.estacionamento.dto;

import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoSaidaDTO {
    private String id;
    private LocalDateTime saida;
    private FormaPagamento formaPagamento;

}
