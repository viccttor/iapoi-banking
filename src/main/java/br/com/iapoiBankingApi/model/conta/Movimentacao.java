package br.com.iapoiBankingApi.model.conta;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "tab_movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao",nullable = true)
    private Integer id;

    @Column(nullable = true, name = "data_hora", length = 30)
    private LocalDateTime dateTime;

    @Column(nullable = true)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true,name = "forma_movimentacao", length = 13)
    private FormaMovimentacao formaMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, name = "tipo_movimentacao", length = 7)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = true, name = "id_conta")
    private Integer idConta;

    @Column(nullable = true)
    private Double valor;

}
