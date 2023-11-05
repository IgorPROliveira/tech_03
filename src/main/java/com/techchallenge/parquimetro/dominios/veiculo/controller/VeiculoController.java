package com.techchallenge.parquimetro.dominios.veiculo.controller;

import com.techchallenge.parquimetro.dominios.veiculo.dto.VeiculoDTO;
import com.techchallenge.parquimetro.dominios.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculo(@RequestParam("cpf") String cpf
                                             ,@Valid @RequestBody VeiculoDTO veiculoDTO){

        return veiculoService.cadastrarVeiculo(cpf,veiculoDTO);
    }

    @GetMapping
    public ResponseEntity<?> listarVeiculos(Pageable pageable){

        return veiculoService.listarVeiculos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterVeiculoPorId(@PathVariable String id){
        return veiculoService.obterVeiculoPorId(id);
    }
}
