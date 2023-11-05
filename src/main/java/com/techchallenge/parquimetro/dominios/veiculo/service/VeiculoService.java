package com.techchallenge.parquimetro.dominios.veiculo.service;

import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.repository.CondutorRepository;
import com.techchallenge.parquimetro.dominios.veiculo.dto.VeiculoDTO;
import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;
import com.techchallenge.parquimetro.dominios.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private CondutorRepository condutorRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository, CondutorRepository condutorRepository) {
        this.veiculoRepository = veiculoRepository;
        this.condutorRepository = condutorRepository;
    }

    public ResponseEntity<?> cadastrarVeiculo(String cpf, VeiculoDTO veiculoDTO){

        try{
            Condutor condutor = this.condutorRepository.findById(cpf).orElse(null);

            if(condutor == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não foi encontrado condutor para o cadastro do veiculo");

            List<Veiculo> veiculos = condutor.getVeiculos();
            if(veiculos == null) {
                veiculos = new ArrayList<>();
                veiculos.add(veiculoDTO.toEntity());

            } else {
                if(!veiculos.contains(veiculoDTO.toEntity()))
                {
                    veiculos.add(veiculoDTO.toEntity());
                }
            }

           condutor.setVeiculos(veiculos);
           condutorRepository.save(condutor);

            Veiculo veiculoExiste = veiculoRepository.findById(veiculoDTO.getPlaca()).orElse(null);

            if(veiculoExiste == null)
                   return ResponseEntity.status(HttpStatus.CREATED)
                           .body( veiculoRepository.save(veiculoDTO.toEntity()));
            else
                return ResponseEntity.status(HttpStatus.OK)
                        .body(veiculoDTO.toEntity());

        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }


    }


    public ResponseEntity<?> listarVeiculos(Pageable pageable) {


        Page<Veiculo> veiculos = this.veiculoRepository.findAll(pageable);
        return ResponseEntity.ok(veiculos);
    }


    public ResponseEntity<?> obterVeiculoPorId(String id) {

        try {
            Veiculo veiculo = this.veiculoRepository
                    .findById(id)
                    .orElse(null);

            if (veiculo == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("veiculo não encontrado");
            else
                return ResponseEntity.status(HttpStatus.OK).body(veiculo);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

}
