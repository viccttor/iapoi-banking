package br.com.iapoiBankingApi.service.cliente;

import br.com.iapoiBankingApi.dto.cliente.ClienteDto;
import br.com.iapoiBankingApi.model.cliente.Cliente;
import br.com.iapoiBankingApi.model.conta.StatusConta;
import br.com.iapoiBankingApi.service.conta.ContaService;
import br.com.iapoiBankingApi.repository.ClienteRepository;
import br.com.iapoiBankingApi.util.FormatadorUtil;
import br.com.iapoiBankingApi.util.ValidadorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    private ContaService contaService = new ContaService();

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public String cadastrarCliente(ClienteDto clienteDto){

        try {

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());

        if(ValidadorUtil.validarCpf(clienteDto.getCpf())) {
            cliente.setCpf(FormatadorUtil.removerCaractersEspeciais(clienteDto.getCpf()));
        } else return "CPF Inválido";

        if(clienteDto.getTelefone() == null) return "Número de telefone Nulo!";
        cliente.setTelefone(FormatadorUtil.removerCaractersEspeciais(clienteDto.getTelefone()));

        cliente.setEmail(clienteDto.getEmail());
        cliente.setConta(contaService.criarConta(clienteDto.getTipoConta()));
        cliente.setEndereco(EnderecoService.cadastrarEndereco(clienteDto.getEnderecoDto()));



        clienteRepository.save(cliente);

        }catch (Exception e) {
            return "Dados Inválidos! Verique os dados e tente novamente!" +
                    "\nDica: CPF ou/e email ou/e telefone já cadastrados ;)";
        }

        return String.format("Cliente %s Cadastrado com Sucesso!",clienteDto.getNome());
    }
    public String buscarSaldoCliente(String id_cliente) {

        try {

            Integer id = Integer.parseInt(id_cliente);
            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if (cliente == null) return "ID da conta não existe!";

            return String.format("=================================" +
                    "\n\nSaldo Atual: R$ %.2f" +
                    "\n\n================================="
                    ,cliente.getConta().getSaldo());

        } catch (Exception e){
            return "ID Inválido!";
        }
    }
    public List<Cliente> buscarCliente(String cpf){
        return clienteRepository.findByCpf(FormatadorUtil.removerCaractersEspeciais(cpf));
    }

    public String deletarCliente(Integer id) {

        try {

            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if (cliente == null) return String.format("Cliente do id: %s não localizado!",id);

            clienteRepository.deleteById(id);
            return String.format("ID: %d, deletado com sucesso!",id);

        }catch (Exception e){
            return "Dados Inválidos!";
        }
    }

    public String ativarConta(Integer idConta){
        Cliente cliente = clienteRepository.findById(idConta).orElse(null);

        if (cliente == null) return "Cliente não encontrado!";
        if (cliente.getConta().getContaStatus() == StatusConta.ATIVA) return "Conta já ativa!";
            cliente.getConta().setContaStatus(StatusConta.ATIVA);
            clienteRepository.save(cliente);
            return String.format("Conta Ativada com Sucesso!\n Cliente: %s\n" +
                    " Conta: %s",cliente.getNome(),cliente.getConta().getNumConta());

    }

    public String inativarConta(Integer idConta){
        Cliente cliente = clienteRepository.findById(idConta).orElse(null);
        if (cliente == null ) {
            return "Conta não localizada!";
        } else if (cliente.getConta().getSaldo() == 0 ){
            if (cliente.getConta().getContaStatus() == StatusConta.INATIVA) return "Conta já Inativa!";
            cliente.getConta().setContaStatus(StatusConta.INATIVA);
            clienteRepository.save(cliente);
            return String.format("Conta Inativada com Sucesso!\n Cliente: %s\n" +
                    " Conta: %s",cliente.getNome(),cliente.getConta().getNumConta());
        } else if (cliente.getConta().getSaldo() > 0) {
            return "Conta com saldo! Operação não permitida!";
        } else {
            return "Operação não permitida!";
        }
    }
}
