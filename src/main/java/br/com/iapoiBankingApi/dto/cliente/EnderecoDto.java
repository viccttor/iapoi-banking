package br.com.iapoiBankingApi.dto.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDto {
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
}
