package com.techchallenge.parquimetro.dominios.condutor.model;

import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document
public class Condutor {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private FormaPagamento formaPagamento;
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    @DBRef
    private List<Veiculo> veiculos;

}
