package com.techchallenge.parquimetro.dominios.condutor.repository;

import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CondutorRepository  extends MongoRepository<Condutor, String> {

    @Query("{ $and: [{'cpf':{$eq:?0}},{'placa':{$eq:?1}}] }")
    public Condutor obterPorCpfEPlaca(String cpf, String placa);
}
