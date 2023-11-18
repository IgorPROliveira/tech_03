package com.techchallenge.parquimetro.dominios.estacionamento.controller;

import com.techchallenge.parquimetro.dominios.condutor.dto.CondutorDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoEntradaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoSaidaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.ReciboDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.model.Estacionamento;
import com.techchallenge.parquimetro.dominios.estacionamento.service.EstacionamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Iniciar Estacionamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estacionamento Iniciado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoEntradaDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<?> iniciarEstacionamento(@Valid @RequestBody EstacionamentoEntradaDTO estacionamentoEntradaDTO){
        return  estacionamentoService.iniciarEstacionamento(estacionamentoEntradaDTO);

    }

    @Operation(summary = "Encerrar Estacionamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estacionamento encerrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoSaidaDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @PutMapping
    public ResponseEntity<?> encerrarEstacionamento(@Valid @RequestBody EstacionamentoSaidaDTO estacionamentoSaidaDTO){

        return  estacionamentoService.encerrarEstacionamento(estacionamentoSaidaDTO);
    }


    @Operation(summary = "Listar Estacionamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamentos listados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoSaidaDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<?> listarTodosEstacionamentos(){
        return estacionamentoService.listarTodosEstacionamentos();
    }


    @Operation(summary = "Listar Estacionamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamento listado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Estacionamento.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<?> obterEstacionamentoPorId(@PathVariable("id") String id){

        return estacionamentoService.obterEstacionamentoPorId(id);
    }

    @Operation(summary = "Apresentar recibo do Estacionamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recibo listado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReciboDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping("recibo/{id}")
    public ResponseEntity<?> gerarRecibo(@PathVariable("id") String id){

        return estacionamentoService.gerarRecibo(id);
    }
}
