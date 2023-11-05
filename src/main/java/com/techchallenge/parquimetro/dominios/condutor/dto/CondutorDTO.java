package com.techchallenge.parquimetro.dominios.condutor.dto;


import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
@ApiModel(description = "Representa uma coleção de Condutor")
public class CondutorDTO {

    @ApiModelProperty(value = "Informacao cpf do condutor", example = "33129064079", position = 1)
    @NotNull(message = "CPF é obrigatório")
    @CPF(message = "CPF deve ser válido")
    private String cpf;

    @ApiModelProperty(value = "Informacao nome do condutor", example = "Arlei", position = 1)
    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @ApiModelProperty(value = "Informacao email do condutor", example = "Arlei.lepiani@homail.com", position = 1)
    @NotNull(message = "email é obrigatório")
    @Email(message = "email deve ser valido")
    private String email;

    @ApiModelProperty(value = "Informacao de forma de pagamento do condutor", example = "PIX,CARTAO_DEBITO,CARTAO_CREDITO", position = 1)
    @NotNull(message = "Informacao de forma de pagamento  é obrigatória")
    private FormaPagamento formaPagamento;

    @ApiModelProperty(value = "Informacao Pais do Endereco condutor", example = "Brasil", position = 1)
    //@NotBlank(message="Informacao Pais do Endereco condutor é obrigatória")
    private String pais;

    @ApiModelProperty(value = "Informacao Estado do Endereco condutor", example = "SP", position = 1)
    //@NotBlank(message="Informacao Estado do Endereco condutor é obrigatória")
    private String estado;

    @ApiModelProperty(value = "Informacao da Cidade do Endereco condutor", example = "Catanduva", position = 1)
    //@NotBlank(message="Informacao da Cidade do Endereco condutor é obrigatória")
    private String cidade;

    @ApiModelProperty(value = "Informacao do CEP do Endereco do condutor", example = "01307-000", position = 1)
    //@NotBlank(message="Informacao do CEP do Endereco do condutor é obrigatória")
    private String cep;

    @ApiModelProperty(value = "Informacao da rua do Endereco do condutor", example = "Rua da Batatas", position = 1)
    //@NotBlank(message="Informacao da rua do Endereco do condutor é obrigatória")
    private String logradouro;

    @ApiModelProperty(value = "Informacao do numero do Endereco do condutor", example = "100", position = 1)
    //@NotBlank(message="IInformacao do numero do Endereco do condutor é obrigatória")
    private Integer numero;


    @ApiModelProperty(value = "Informacao do complemento do Endereco do condutor", example = "AP 10", position = 1)
    private String complemento;


    public Condutor toEntity() {
        Condutor condutor =
                new Condutor();
        condutor.setCpf(this.getCpf());
        condutor.setEmail(this.getEmail());
        condutor.setFormaPagamento(this.getFormaPagamento());
        condutor.setNome(this.getNome());
        condutor.setPais(this.pais);
        condutor.setEstado(this.estado);
        condutor.setCidade(this.cidade);
        condutor.setCep(this.cep);
        condutor.setLogradouro(this.logradouro);
        condutor.setNumero(this.numero);
        condutor.setComplemento(this.complemento);


        //List<Veiculo> veiculos = new ArrayList<>();
        //this.getVeiculos().forEach(veiculo -> veiculos.add(veiculo.toEntity()));
        //condutor.setVeiculos(veiculos);


        return condutor;
    }


    public CondutorDTO toDTO(Condutor entity) {
        CondutorDTO condutorDTO = new CondutorDTO();

        condutorDTO.setCpf(entity.getCpf());
        condutorDTO.setEmail(entity.getEmail());
        condutorDTO.setNome(entity.getEmail());
        condutorDTO.setFormaPagamento(entity.getFormaPagamento());
        condutorDTO.setPais(entity.getPais());
        condutorDTO.setEstado(entity.getEstado());
        condutorDTO.setCidade(entity.getCidade());
        condutorDTO.setCep(entity.getCep());
        condutorDTO.setLogradouro(entity.getLogradouro());
        condutorDTO.setNumero(entity.getNumero());
        condutorDTO.setComplemento(entity.getComplemento());


        return condutorDTO;
    }
}
