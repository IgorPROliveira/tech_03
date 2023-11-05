package com.techchallenge.parquimetro.dominios.estacionamento.dto;

import com.techchallenge.parquimetro.dominios.estacionamento.model.PeriodoEstacionamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoEntradaDTO {
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private PeriodoEstacionamento periodoEstacionamento;
    private String condutorCPF;
    private String veiculoPlaca;


}
