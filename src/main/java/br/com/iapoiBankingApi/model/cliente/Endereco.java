package br.com.iapoiBankingApi.model.cliente;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tab_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, name = "id")
    private Integer id;

    @Column(nullable = true, length = 10)
    private String cep;

    @Column(nullable = true, length = 30)
    private String cidade;

    @Column(nullable = true, length = 3)
    private String estado;

    @Column(nullable = true, length = 30)
    private String bairro;

    @Column(nullable = true, length = 50)
    private String logradouro;

    @Column(nullable = true, length = 5)
    private String numero;

    @Column(nullable = true)
    private String complemento;
}
