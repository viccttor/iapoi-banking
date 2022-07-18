package br.com.iapoiBankingApi.service.cliente;

import br.com.iapoiBankingApi.dto.cliente.EnderecoDto;
import br.com.iapoiBankingApi.model.cliente.Cliente;
import br.com.iapoiBankingApi.model.cliente.Endereco;
import br.com.iapoiBankingApi.repository.ClienteRepository;
import br.com.iapoiBankingApi.repository.EnderecoRepository;
import br.com.iapoiBankingApi.util.ValidadorUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarEnderecoCliente(Integer id){
        return enderecoRepository.findById(id); // Está trazendo o ID do Endereço ao invés do cliente
    }

    public static Endereco cadastrarEndereco(@NotNull EnderecoDto enderecoDto){

        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDto.getCep());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setComplemento(enderecoDto.getComplemento());

        return endereco;
    }

    public String alterarEndereco(Integer idEndereco, EnderecoDto enderecoDto){
        try {
            Endereco endereco = enderecoRepository.findById(idEndereco).orElse(null);
            if (endereco == null) return "Endereço não Encontrado!";
            endereco.setId(idEndereco);

            if (ValidadorUtil.validarTamanhoString(10,enderecoDto.getCep()))
                return "CEP Inválido! Permitido apenas 10 caracteres!";
            endereco.setCep(enderecoDto.getCep());

            if (ValidadorUtil.validarTamanhoString(30,enderecoDto.getCidade()))
                return "Cidade Inválido! Permitido apenas 30 caracteres!";
            endereco.setCidade(enderecoDto.getCidade());

            if (ValidadorUtil.validarTamanhoString(3,enderecoDto.getEstado()))
                return "Estado Inválido! Permitido apenas 2 caracteres!";
            endereco.setEstado(enderecoDto.getEstado().toUpperCase());

            if (ValidadorUtil.validarTamanhoString(30,enderecoDto.getBairro()))
                return "Bairro Inválido! Permitido apenas 30 caracteres!";
            endereco.setBairro(enderecoDto.getBairro());

            if (ValidadorUtil.validarTamanhoString(50,enderecoDto.getLogradouro()))
                return "Logradouro Inválido! Permitido apenas 50 caracteres!";
            endereco.setLogradouro(enderecoDto.getLogradouro());

            if (ValidadorUtil.validarTamanhoString(6,enderecoDto.getNumero()))
                return "Numero Inválido! Permitido apenas 5 caracteres!";
            endereco.setNumero(enderecoDto.getNumero());

            endereco.setComplemento(enderecoDto.getComplemento());
            enderecoRepository.save(endereco);

            return "Endederco Alterado com sucesso!";
        } catch (Exception e){
            return "Dados inválidos!";
        }

    }
}
