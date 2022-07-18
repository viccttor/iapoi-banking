package br.com.iapoiBankingApi.model.conta;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Embeddable
@Table(name = "tab_conta")
public class Conta {

    @Column(nullable = true, length = 5)
    private String agencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, name = "tipo_conta", length = 8)
    private TipoConta tipoConta;

    @Column(unique = true, name = "num_conta", length = 15)
    private String numConta;

    @Column(nullable = true)
    private Double saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, name = "conta_status",length = 7)
    private StatusConta contaStatus;

}
