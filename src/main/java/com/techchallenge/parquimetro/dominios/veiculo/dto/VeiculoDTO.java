package com.techchallenge.parquimetro.dominios.veiculo.dto;


import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class VeiculoDTO {

    @NotBlank(message="Informacao da Placa do Veiculo é obrigatória")
    private String placa;

    @NotBlank(message="Informacao do modelo do Veiculo é obrigatória")
    private String modelo;


    public Veiculo toEntity() {
        Veiculo veiculo =
                new Veiculo();
        veiculo.setPlaca(this.getPlaca());
        veiculo.setModelo(this.getModelo());
        return veiculo;
    }


    public VeiculoDTO toDTO(Veiculo entity) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setPlaca(entity.getPlaca());
        veiculoDTO.setModelo(entity.getModelo());
        return veiculoDTO;
    }

}
