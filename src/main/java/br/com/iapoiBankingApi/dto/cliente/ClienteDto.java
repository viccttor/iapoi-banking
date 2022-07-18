package br.com.iapoiBankingApi.dto.cliente;

import br.com.iapoiBankingApi.model.conta.TipoConta;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteDto {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private TipoConta tipoConta;
    private EnderecoDto enderecoDto;



}
