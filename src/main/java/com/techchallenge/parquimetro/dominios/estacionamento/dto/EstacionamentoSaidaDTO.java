package com.techchallenge.parquimetro.dominios.estacionamento.dto;

import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoSaidaDTO {
    @NotNull(message ="id deve ser preenchido")
    private String id;
    private LocalDateTime saida;
    @NotNull(message = "Informacao de forma de pagamento  é obrigatória")
    private FormaPagamento formaPagamento;

}
