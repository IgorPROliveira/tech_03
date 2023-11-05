package com.techchallenge.parquimetro.dominios.condutor.controller;

import com.techchallenge.parquimetro.dominios.condutor.dto.CondutorDTO;
import com.techchallenge.parquimetro.dominios.condutor.service.CondutorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    private CondutorService condutorService;

    public CondutorController(CondutorService condutorService) {
             this.condutorService = condutorService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCondutor(@Valid @RequestBody CondutorDTO condutorDTO){
         return condutorService.cadastrarCondutor(condutorDTO);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosCondutores(Pageable pageable){
        return condutorService.listarCondutores(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterCondutorPorId(@PathVariable String id){
        return condutorService.obterCondutorPorId(id);
    }

    @PutMapping
    public ResponseEntity<?> atualizaCondutor(@Valid @RequestBody CondutorDTO condutorDTO){
        return condutorService.atualizarCondutor(condutorDTO);
    }

    //Arlei

}
