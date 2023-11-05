package com.techchallenge.parquimetro.dominios.veiculo.dto;


import com.techchallenge.parquimetro.dominios.veiculo.model.Veiculo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Representa uma coleção de Veiculos")
@Getter
@Setter
public class VeiculoDTO {

    @ApiModelProperty(value = "Informacao da Placa do Veiculo", example = "DNA5D67", position = 1)
    //@NotBlank(message="Informacao da Placa do Veiculo é obrigatória")
    private String placa;

    @ApiModelProperty(value = "Informacao do modelo do Veiculo", example = "VW GOL", position = 1)
    //@NotBlank(message="Informacao do modelo do Veiculo é obrigatória")
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
