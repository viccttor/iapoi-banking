package br.com.iapoiBankingApi.model.cliente;

import br.com.iapoiBankingApi.model.conta.Conta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tab_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, name = "id_cliente")
    private Integer id;

    @Column(unique = true, nullable = true, length = 50)
    private String email;

    @Column(unique = true, nullable = true, length = 14)
    private String telefone;

    @Column(nullable = true, length = 50)
    private String nome;

    @Column(unique = true, nullable = true, length = 14)
    private String cpf;

    @Embedded
    private Conta conta;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}
