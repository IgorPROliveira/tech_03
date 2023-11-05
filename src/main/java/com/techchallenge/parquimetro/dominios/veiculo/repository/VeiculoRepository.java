package com.techchallenge.parquimetro.dominios.veiculo.repository;


import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

}
