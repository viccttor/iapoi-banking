package br.com.iapoiBankingApi.service.conta;

import br.com.iapoiBankingApi.dto.conta.MovimentacaoDto;
import br.com.iapoiBankingApi.dto.conta.MovimentacaoEntreContasDto;
import br.com.iapoiBankingApi.model.cliente.Cliente;
import br.com.iapoiBankingApi.model.conta.*;
import br.com.iapoiBankingApi.repository.ClienteRepository;
import br.com.iapoiBankingApi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Movimentacao> listarMovimentacao(){
        return movimentacaoRepository.findAll();
    }
    public String novaMovimentacao(MovimentacaoDto movimentacaoDto){

        try {

            List<Cliente> todosClientes = clienteRepository.findAll();
            Cliente localizaCliente = new Cliente();
            for (Cliente cliente: todosClientes){
                if (cliente.getId().equals(movimentacaoDto.getIdConta())){
                    localizaCliente = cliente;
                }
            }

            if (localizaCliente.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta Inativa! " +
                    "Operação não permitida!";
            if( movimentacaoDto.getFormaMovimentacao() == FormaMovimentacao.RESGATE) return "Forma de movimentacação " +
                    "Resgate é inválida neste escopo!";
            if (movimentacaoDto.getValor() < 0) return String.format("Valor da movimentação negativo!\n" +
                    "Valor: %.2f. Só é permitido a entrada de valor positivo",movimentacaoDto.getValor());

            if (movimentacaoDto.getTipoMovimentacao().equals(TipoMovimentacao.DESPESA)
                    && !verificarContaStatusMovimentacao(movimentacaoDto.getValor(),localizaCliente.getConta()))
                return String.format("Infelizmente não foi possivel realizar a operação :(" +
                        "\nValor do saque excede ao em conta. \nSaldo: %f",localizaCliente.getConta().getSaldo());

            if (localizaCliente.getConta().getTipoConta().equals(TipoConta.POUPANCA) &&
                    movimentacaoDto.getTipoMovimentacao().equals(TipoMovimentacao.DESPESA)) {
                        return "Operação Despesa não permitida para conta Poupança!";
            }

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setIdConta(movimentacaoDto.getIdConta());
            movimentacao.setDateTime(LocalDateTime.now());
            movimentacao.setFormaMovimentacao(movimentacaoDto.getFormaMovimentacao());
            movimentacao.setTipoMovimentacao(movimentacaoDto.getTipoMovimentacao());
            movimentacao.setDescricao(movimentacaoDto.getDescricao());

            Double valor = movimentacaoDto.getTipoMovimentacao() == TipoMovimentacao.RECEITA ? movimentacaoDto.getValor()
                    : movimentacaoDto.getValor() * -1;
            if (movimentacaoDto.getTipoMovimentacao().equals(TipoMovimentacao.DESPESA) && movimentacaoDto.getValor() < 0){
                valor = valor * -1;
            }
            movimentacao.setValor(valor);
            Cliente cliente = clienteRepository.findById(movimentacaoDto.getIdConta()).orElse(null);
            if (cliente != null) {
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
            }

            clienteRepository.save(cliente);
            movimentacaoRepository.save(movimentacao);

            return String.format("Movimentação Realizada com Sucesso!" +
                        "\n===============================================\n" +
                        "\nTipo: %s\n" +
                        "\nValor: R$ %.2f" +
                        "\n\n===============================================\n"
                        ,movimentacaoDto.getTipoMovimentacao(),movimentacaoDto.getValor());


        }catch (Exception e){
            return "Infelismente não foi possível realizar está operação! /:";
        }

    }

    public String NovaMovimentacaoEntreContas(MovimentacaoEntreContasDto movimentacaoEntreContasDto) {

        try {

            Cliente clientePagador = clienteRepository.findById(movimentacaoEntreContasDto.getIdContaPagadora())
                    .orElse(null);
            Cliente clienteRecebedor = clienteRepository.findById(movimentacaoEntreContasDto.getIdContaRecebedora())
                    .orElse(null);


            if (clientePagador == null){
                return "ID da conta pagadora não existe!";
            } else if (clienteRecebedor == null) {
                return "ID da conta recebedora não existe!";
            }
            if (clientePagador.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta Pagadora está Inativa!" +
                    "Operação não permitida!";
                    else if (clienteRecebedor.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta Recebedora está Inativa! " +
                    "Operação não permitida!";

            if (clientePagador.getConta().getTipoConta().equals(TipoConta.POUPANCA))
                return "Operação não permitida para conta Poupança!";

            if (movimentacaoEntreContasDto.getValor() < 0) return String.format("Valor da movimentação negativo!\n" +
                    "Valor: %.2f. Só é permitido a entrada de valor positivo",movimentacaoEntreContasDto.getValor());


            if (verificarContaStatusMovimentacao(movimentacaoEntreContasDto.getValor(),clientePagador.getConta())) {

                LocalDateTime dataHora = LocalDateTime.now();
                String descricaoPagador = movimentacaoEntreContasDto.getDescricaoPagador();
                String descricaoRecebedor = movimentacaoEntreContasDto.getDescricaoRecebedor();
                Double valor = movimentacaoEntreContasDto.getValor();

                Movimentacao movimentacaoPagador = new Movimentacao();
                movimentacaoPagador.setDateTime(dataHora);
                movimentacaoPagador.setDescricao(descricaoPagador);
                movimentacaoPagador.setFormaMovimentacao(FormaMovimentacao.TRANSFERENCIA);
                movimentacaoPagador.setTipoMovimentacao(TipoMovimentacao.DESPESA);
                movimentacaoPagador.setValor(valor * -1);
                clientePagador.getConta().setSaldo(clientePagador.getConta().getSaldo() - valor);
                movimentacaoPagador.setIdConta(movimentacaoEntreContasDto.getIdContaPagadora());
                clienteRepository.save(clientePagador);
                movimentacaoRepository.save(movimentacaoPagador);

                Movimentacao movimentacaoRecebedor = new Movimentacao();
                movimentacaoRecebedor.setDateTime(dataHora);
                movimentacaoRecebedor.setDescricao(descricaoRecebedor);
                movimentacaoRecebedor.setFormaMovimentacao(FormaMovimentacao.TRANSFERENCIA);
                movimentacaoRecebedor.setTipoMovimentacao(TipoMovimentacao.RECEITA);
                movimentacaoRecebedor.setValor(valor);
                clienteRecebedor.getConta().setSaldo(clienteRecebedor.getConta().getSaldo() + valor);
                movimentacaoRecebedor.setIdConta(movimentacaoEntreContasDto.getIdContaRecebedora());
                clienteRepository.save(clienteRecebedor);
                movimentacaoRepository.save(movimentacaoRecebedor);

                return String.format("Transferência Realizada com Sucesso!" +
                        "\n=============================================================\n" +
                        "\nValor: R$ %.2f" +
                         "\n\n=============================================================\n" +
                        "\nOrigen\n\nNome: %s\nConta: %s\nTipo da Conta: %s\nDescrição: %s" +
                         "\n\n=============================================================\n" +
                        "\nDestino\n\nNome: %s\nConta: %s\nTipo da Conta: %s\nDescrição: %s" +
                        "\n\n=============================================================\n",
                        movimentacaoEntreContasDto.getValor()
                        ,clientePagador.getNome(),clientePagador.getConta().getNumConta(),clientePagador.getConta()
                                .getTipoConta(),movimentacaoEntreContasDto.getDescricaoPagador()
                        ,clienteRecebedor.getNome(),clienteRecebedor.getConta().getNumConta(),clienteRecebedor
                                .getConta().getTipoConta(),movimentacaoEntreContasDto.getDescricaoRecebedor()
                );
            }
        }catch (Exception e){
            return "Infelismente não foi possível realizar está operação! /:";
        }
        return "ERRO!";
    }

    public String saque(Integer id, Double valorSaque) {

        try {

            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if (cliente.getConta().getTipoConta() == TipoConta.POUPANCA) return "Operação não pode ser realizada devido ao tipo de Conta ser Poupança!";

            if (cliente.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta Invativa! Operação Inválida";

            if (valorSaque > 0 && verificarContaStatusMovimentacao(valorSaque, cliente.getConta())) {

                Movimentacao movimentacaoSaque = new Movimentacao();
                movimentacaoSaque.setDateTime(LocalDateTime.now());
                movimentacaoSaque.setDescricao("SAQUE EM CAIXA ELETRÔNICO");
                movimentacaoSaque.setFormaMovimentacao(FormaMovimentacao.SAQUE);
                movimentacaoSaque.setIdConta(cliente.getId());
                movimentacaoSaque.setTipoMovimentacao(TipoMovimentacao.DESPESA);
                movimentacaoSaque.setValor(valorSaque * -1);

                cliente.getConta().setSaldo(cliente.getConta().getSaldo()-valorSaque);

                clienteRepository.save(cliente);
                movimentacaoRepository.save(movimentacaoSaque);

                return String.format("Saque Realizado com Sucesso! " +
                        "\n======================================================\n\n" +
                        "Valor: R$ %.2f \n\nSALDO EM ATUAL DA CONTA APÓS O SAQUE " +
                        "\n\nSaldo: R$ %.2f "+
                        "\n\n=====================================================\n"
                        ,valorSaque,cliente.getConta().getSaldo());

            } else if (valorSaque > cliente.getConta().getSaldo()) {
                return String.format("Infelizmente não foi possivel realizar a operação :(" +
                    "\nValor do saque excede ao em conta. \nSaldo: R$ %.2f",cliente.getConta().getSaldo());

            }  return String.format("Infelizmente não foi possivel realizar a operação :(" +
                    "\nValor do saque negativo! Verifique os dados e tente novamente." +
                    "\nValor: R$ %.2f",valorSaque);

        }catch (Exception e) {
            return "Infelizmente não foi possivel realizar a operação :(";
        }
    }

    public List<Movimentacao> listarMovimentacaoPorConta(Integer id){
        return movimentacaoRepository.findByIdConta(id);
    }

    protected boolean verificarContaStatusMovimentacao(Double valor, Conta conta){
        Double contaSaldo = conta.getSaldo();
        double preSaqueValidacao = contaSaldo - valor;
        if (valor < 0 || conta == null || preSaqueValidacao < 0) {
            return false;
        }else {
            return true;
        }
    }

    public String resgatePoupanca(Integer idConta) {
        try {
            Cliente cliente = clienteRepository.findById(idConta).orElse(null);

            if (cliente != null && cliente.getConta().getTipoConta() == TipoConta.POUPANCA){
                if (cliente.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta Invativa! Operação Inválida";
                if (cliente.getConta().getSaldo() == 0) return "Conta já está zerada! Ação de Resgate não permitida!";
                Movimentacao movimentacaoResgate = new Movimentacao();
                movimentacaoResgate.setIdConta(idConta);
                movimentacaoResgate.setDateTime(LocalDateTime.now());
                movimentacaoResgate.setFormaMovimentacao(FormaMovimentacao.RESGATE);
                movimentacaoResgate.setTipoMovimentacao(TipoMovimentacao.DESPESA);
                Double valorResgate = cliente.getConta().getSaldo();
                movimentacaoResgate.setValor(valorResgate * -1);
                movimentacaoResgate.setDescricao("Resgate da Conta realizado com Sucesso!");
                movimentacaoRepository.save(movimentacaoResgate);

                cliente.getConta().setSaldo(0.0);
                clienteRepository.save(cliente);

                return String.format("Regaste Realizado com Sucesso!\nValor: R$ %.2f\nSaldo: R$ %.2f", valorResgate,cliente.getConta().getSaldo());
            } else {
                return "Id informado não corresponde a uma conta Poupança válida!";
            }
        } catch (Exception e){
            return "Não foi possível realizar Operação";
        }
    }
}
