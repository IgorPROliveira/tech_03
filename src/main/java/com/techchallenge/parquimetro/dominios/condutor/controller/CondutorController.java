package com.techchallenge.parquimetro.dominios.condutor.controller;

import com.techchallenge.parquimetro.dominios.condutor.dto.CondutorDTO;
import com.techchallenge.parquimetro.dominios.condutor.service.CondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/condutor")
public class CondutorController {

    private CondutorService condutorService;

    @Autowired
    public CondutorController(CondutorService condutorService) {
             this.condutorService = condutorService;
    }

    @Operation(summary = "Cadastrar condutor pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Condutor Cadastrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondutorDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<?> cadastrarCondutor(@Valid @RequestBody CondutorDTO condutorDTO){
         return condutorService.cadastrarCondutor(condutorDTO);
    }

    @Operation(summary = "Lista condutores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condutores listados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondutorDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<?> listarTodosCondutores(Pageable pageable){
        return condutorService.listarCondutores(pageable);
    }

    @Operation(summary = "Listar condutor por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condutor listado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondutorDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<?> obterCondutorPorId(@PathVariable String id){
        return condutorService.obterCondutorPorId(id);
    }

    @Operation(summary = "Atualizar condutor por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condutor Atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondutorDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @PutMapping
    public ResponseEntity<?> atualizaCondutor(@Valid @RequestBody CondutorDTO condutorDTO){
        return condutorService.atualizarCondutor(condutorDTO);
    }



}
