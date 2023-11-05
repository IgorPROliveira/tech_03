package com.techchallenge.parquimetro.dominios.estacionamento.repository;

import com.techchallenge.parquimetro.dominios.estacionamento.model.Estacionamento;
import com.techchallenge.parquimetro.dominios.estacionamento.model.PeriodoEstacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {

    /*
    O sistema inclui um recurso de alerta que notifica o condutor quando o tempo de estacionamento está prestes a expirar, no caso de horário fixo.
- Para períodos variáveis, o sistema também emite um alerta informando que o sistema estenderá automaticamente o estacionamento por mais uma hora, a menos que o condutor desligue o registro.


    @Query(value="{'status': {$eq:?0}}",sort="{'titulo':1}")
    public List<Estacionamento> obterExpirandosFixo(LocalDateTime dta, PeriodoEstacionamento periodo);

    @Query("{ $and: [{'data':{$gte:?0}},{'data':{$lte:?1}}] }")
    public List<Artigo> obterArtigoPorDataHora(LocalDateTime de, LocalDateTime ate);

     */

    public List<Estacionamento> findEstacionamentosByValorIsNullAndFormaPagamentoIsNull();
}
