package com.techchallenge.parquimetro.dominios.veiculo.controller;


import com.techchallenge.parquimetro.dominios.veiculo.dto.VeiculoDTO;
import com.techchallenge.parquimetro.dominios.veiculo.service.VeiculoService;
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
@RequestMapping("/veiculo")
public class VeiculoController {

    VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(summary = "Cadastrar veículo para o condutor pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo Cadastrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VeiculoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculo(@RequestParam("cpf") String cpf
                                             ,@Valid @RequestBody VeiculoDTO veiculoDTO){

        return veiculoService.cadastrarVeiculo(cpf,veiculoDTO);
    }

    @Operation(summary = "Listar Veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos Listados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VeiculoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<?> listarVeiculos(Pageable pageable){

        return veiculoService.listarVeiculos(pageable);
    }

    @Operation(summary = "Listar Veículos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo Listado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VeiculoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<?> obterVeiculoPorId(@PathVariable String id){
        return veiculoService.obterVeiculoPorId(id);
    }
}
