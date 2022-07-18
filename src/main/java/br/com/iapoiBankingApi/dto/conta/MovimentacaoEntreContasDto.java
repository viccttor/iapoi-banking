package br.com.iapoiBankingApi.dto.conta;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovimentacaoEntreContasDto {
    private Double valor;
    private Integer idContaPagadora;
    private Integer idContaRecebedora;
    private String descricaoPagador;
    private String descricaoRecebedor;
}
