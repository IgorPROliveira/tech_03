package com.techchallenge.parquimetro.dominios.estacionamento.controller;

import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoEntradaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoSaidaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.service.EstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping
    public ResponseEntity<?> iniciarEstacionamento(@Valid @RequestBody EstacionamentoEntradaDTO estacionamentoEntradaDTO){
        return  estacionamentoService.iniciarEstacionamento(estacionamentoEntradaDTO);

    }

    @PutMapping
    public ResponseEntity<?> encerrarEstacionamento(@Valid @RequestBody EstacionamentoSaidaDTO estacionamentoSaidaDTO){

        return  estacionamentoService.encerrarEstacionamento(estacionamentoSaidaDTO);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosEstacionamentos(){
        return estacionamentoService.listarTodosEstacionamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterEstacionamentoPorId(@PathVariable("id") String id){

        return estacionamentoService.obterEstacionamentoPorId(id);
    }

    @GetMapping("recibo/{id}")
    public ResponseEntity<?> gerarRecibo(@PathVariable("id") String id){

        return estacionamentoService.gerarRecibo(id);
    }
}
