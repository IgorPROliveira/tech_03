package com.techchallenge.parquimetro.dominios.estacionamento.dto;

import com.techchallenge.parquimetro.dominios.estacionamento.model.PeriodoEstacionamento;


import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoEntradaDTO {

    @FutureOrPresent(message="Data não poder menor que agora!")
    private LocalDateTime entrada;
    @FutureOrPresent(message="Data não poder menor que agora!")
    private LocalDateTime saida;
    private PeriodoEstacionamento periodoEstacionamento;
    private String condutorCPF;
    @CPF(message="cpf deve ser válido")
    private String veiculoPlaca;


}
