package br.com.iapoiBankingApi.dto.conta;

import br.com.iapoiBankingApi.model.conta.FormaMovimentacao;
import br.com.iapoiBankingApi.model.conta.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovimentacaoDto {

    private Integer idConta;
    private String descricao;
    private Double valor;
    private FormaMovimentacao formaMovimentacao;
    private TipoMovimentacao tipoMovimentacao;
}
