package br.com.iapoiBankingApi.service.conta;

import br.com.iapoiBankingApi.model.cliente.Cliente;
import br.com.iapoiBankingApi.model.conta.Conta;
import br.com.iapoiBankingApi.model.conta.StatusConta;
import br.com.iapoiBankingApi.model.conta.TipoConta;
import br.com.iapoiBankingApi.repository.ClienteRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class ContaService {
    @Autowired
    private ClienteRepository clienteRepository;

    private final String AGENCIA_PADRAO = "0001";

    public Conta criarConta(TipoConta tipoConta){

        Conta conta = new Conta();

        if (tipoConta.equals(TipoConta.POUPANCA)) {
            conta.setContaStatus(StatusConta.ATIVA);
            conta.setTipoConta(tipoConta);
            conta.setAgencia(AGENCIA_PADRAO);
            int numConta = RandomUtils.nextInt(500000,10000000);
            conta.setNumConta(String.format( "%d-%d",numConta,RandomUtils.nextInt(0,5)));
            conta.setSaldo(0d);
            return conta;
        } else if (tipoConta.equals(TipoConta.CORRENTE)) {
            conta.setContaStatus(StatusConta.ATIVA);
            conta.setTipoConta(tipoConta);
            conta.setAgencia(AGENCIA_PADRAO);
            int numConta = RandomUtils.nextInt(10500000,20500000);
            conta.setNumConta(String.format( "%d-%d",numConta,RandomUtils.nextInt(0,5)));
            conta.setSaldo(0d);
            return conta;
        } else return null;

    }

}
