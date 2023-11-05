package com.techchallenge.parquimetro.dominios.condutor.service;

import com.techchallenge.parquimetro.dominios.condutor.dto.CondutorDTO;
import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.repository.CondutorRepository;
import com.techchallenge.parquimetro.dominios.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CondutorService {

    private  CondutorRepository condutorRepository;
    private  VeiculoRepository veiculoRepository;
    private MongoTransactionManager mongoTransactionManager;
    private MongoTemplate mongoTemplate;

    @Autowired
    public CondutorService(CondutorRepository condutorRepository, VeiculoRepository veiculoRepository, MongoTransactionManager mongoTransactionManager, MongoTemplate mongoTemplate) {
        this.condutorRepository = condutorRepository;
        this.veiculoRepository = veiculoRepository;
        this.mongoTransactionManager = mongoTransactionManager;
        this.mongoTemplate = mongoTemplate;
    }


    public ResponseEntity<?> cadastrarCondutor(CondutorDTO condutorDTO) {

        Condutor condutorexistente = this.condutorRepository
                .findById(condutorDTO.getCpf())
                .orElse(null);

        if (condutorexistente == null) {
            Condutor condutor = condutorDTO.toEntity();
            this.condutorRepository.save(condutor);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Condutor já exitente, usar metodo de atualização");
        }
    }



    public ResponseEntity<?> listarCondutores(Pageable pageable) {


        Page<Condutor> condutores = this.condutorRepository.findAll(pageable);
        return ResponseEntity.ok(condutores);
    }


    public ResponseEntity<?> obterCondutorPorId(String id) {

        try {
            Condutor condutor = this.condutorRepository
                    .findById(id)
                    .orElse(null);

            if (condutor == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Condutor não encontrado");
            else
                return ResponseEntity.status(HttpStatus.OK).body(condutor);

        } catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<?> atualizarCondutor(CondutorDTO condutorDTO) {

        Condutor condutorexistente = this.condutorRepository
                .findById(condutorDTO.getCpf())
                .orElse(null);

        if (condutorexistente == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Condutor não encontrado");
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.condutorRepository.save(condutorDTO.toEntity()));
    }

}
