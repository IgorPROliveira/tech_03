package com.techchallenge.parquimetro.dominios.estacionamento.service;

import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;
import com.techchallenge.parquimetro.dominios.condutor.repository.CondutorRepository;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoEntradaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.EstacionamentoSaidaDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.dto.ReciboDTO;
import com.techchallenge.parquimetro.dominios.estacionamento.model.Estacionamento;
import com.techchallenge.parquimetro.dominios.estacionamento.model.PeriodoEstacionamento;
import com.techchallenge.parquimetro.dominios.estacionamento.repository.EstacionamentoRepository;
import com.techchallenge.parquimetro.dominios.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstacionamentoService {

    private CondutorRepository condutorRepository;
    private VeiculoRepository veiculoRepository;
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    public EstacionamentoService(CondutorRepository condutorRepository, VeiculoRepository veiculoRepository, EstacionamentoRepository estacionamentoRepository) {
        this.condutorRepository = condutorRepository;
        this.veiculoRepository = veiculoRepository;
        this.estacionamentoRepository = estacionamentoRepository;
    }

    public ResponseEntity<?> iniciarEstacionamento(EstacionamentoEntradaDTO estacionamentoEntradaDTO)
    {
        // validar se existe condutor
        Condutor condutor = this.condutorRepository.findById(estacionamentoEntradaDTO.getCondutorCPF()).orElse(null);

        if(condutor == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado condutor , favor proceder com o cadastro do Condutor");

        // validar se existe veiculo no condutor
        Condutor condutorPlaca = this.condutorRepository.findById(estacionamentoEntradaDTO.getCondutorCPF()).orElse(null);

        if(condutorPlaca == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado o veiculo para o condutor , favor proceder com o cadastro do veiculo");


        // vaildar data de saida se for tipo de periodo fixo
        if(estacionamentoEntradaDTO.getPeriodoEstacionamento() == PeriodoEstacionamento.PERIODO_FIXO
                && estacionamentoEntradaDTO.getSaida() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A Data de saída deve ser informada");

        Estacionamento estacionamento =
                new Estacionamento();

        estacionamento.setDtEntrada(LocalDateTime.now());
        estacionamento.setDtSaida(estacionamentoEntradaDTO.getSaida());
        estacionamento.setPeriodoEstacionamento(estacionamentoEntradaDTO.getPeriodoEstacionamento());
        estacionamento.setCondutor(condutor);
        estacionamento.setVeiculo(veiculoRepository.findById(estacionamentoEntradaDTO.getVeiculoPlaca()).orElse(null));

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoRepository.save(estacionamento));
        } catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }

    }

    public ResponseEntity<?> encerrarEstacionamento(EstacionamentoSaidaDTO estacionamentoSaidaDTO) {

        Estacionamento estacionamento = this.estacionamentoRepository.findById(estacionamentoSaidaDTO.getId()).orElse(null);

        if(estacionamento == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado estacionamento");

        // caso seja periodo fixo, forçar a data informada na entrada
        if(estacionamento.getPeriodoEstacionamento() == PeriodoEstacionamento.PERIODO_FIXO)
            estacionamentoSaidaDTO.setSaida(estacionamento.getDtSaida());
         else
            estacionamentoSaidaDTO.setSaida(LocalDateTime.now());

        estacionamento.setFormaPagamento(estacionamentoSaidaDTO.getFormaPagamento());
        estacionamento.setDtSaida(estacionamentoSaidaDTO.getSaida());
        calcularRecibo(estacionamento);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(estacionamentoRepository.save(estacionamento));
        }catch(Exception ex){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }



    public ResponseEntity<?> listarTodosEstacionamentos() {
        List<Estacionamento> estacionamentos =    estacionamentoRepository.findAll();

        return ResponseEntity.ok(estacionamentos);
    }


    public void calcularRecibo(Estacionamento estacionamento) {
        long minutos =
                Duration.between(estacionamento.getDtEntrada(), estacionamento.getDtSaida()).toMinutes();
        double horas = minutos / 60.0;
        int horasTotal= (int) Math.ceil(horas);
        estacionamento.setValor(BigDecimal.valueOf(horasTotal * 10));
    }


    public ResponseEntity<?> obterEstacionamentoPorId(String id){
        try {
            Estacionamento estacionamento = this.estacionamentoRepository
                    .findById(id)
                    .orElse(null);

            if (estacionamento == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("estacionamento não encontrado");
            else
                return ResponseEntity.status(HttpStatus.OK).body(estacionamento);

        } catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<?> gerarRecibo(String id){

        Estacionamento estacionamento = this.estacionamentoRepository
                .findById(id)
                .orElse(null);

        if (estacionamento == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("estacionamento não encontrado");

        ReciboDTO recibo = new ReciboDTO();
        String nome = estacionamento.getCondutor().getNome();
        String cpf = estacionamento.getCondutor().getCpf();
        BigDecimal  valor = estacionamento.getValor();
        LocalDateTime data = estacionamento.getDtSaida();
        String forma;
        if(estacionamento.getFormaPagamento() == FormaPagamento.CARTAO_CREDITO)
            forma = "Cartão de Débito";
        else if (estacionamento.getFormaPagamento() == FormaPagamento.CARTAO_CREDITO) {
            forma = "Cartão de Crédito";
        } else
            forma = "Pix";

        recibo.setMensagem("Recebemos do Sr(a) :" + nome + " CPF :" + cpf + " o valor de R$ " + valor
        + " na data de: " + data + " na forma de " + forma );

        return ResponseEntity.status(HttpStatus.OK).body(recibo.getMensagem());
    }

}
