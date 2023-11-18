package com.techchallenge.parquimetro.dominios.condutor.dto;

import com.techchallenge.parquimetro.dominios.condutor.model.Condutor;
import com.techchallenge.parquimetro.dominios.condutor.model.FormaPagamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;



@Getter
@Setter
public class CondutorDTO {

    @NotNull(message = "CPF é obrigatório")
    @CPF(message = "CPF deve ser válido")
    private String cpf;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotNull(message = "email é obrigatório")
    @Email(message = "email deve ser valido")
    private String email;

    @NotNull(message = "Informacao de forma de pagamento  é obrigatória")
    private FormaPagamento formaPagamento;

    private String pais;

    private String estado;

    private String cidade;

    private String cep;

    private String logradouro;

    private Integer numero;

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
