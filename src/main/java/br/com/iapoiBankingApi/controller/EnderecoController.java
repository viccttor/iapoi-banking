package br.com.iapoiBankingApi.controller;

import br.com.iapoiBankingApi.dto.cliente.EnderecoDto;
import br.com.iapoiBankingApi.model.cliente.Endereco;
import br.com.iapoiBankingApi.repository.EnderecoRepository;
import br.com.iapoiBankingApi.service.cliente.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/listar-todos-enderecos")
    public List<Endereco> listarEnderecos(){
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/endereco-cliente")
    public Optional<Endereco> buscarEnderecoCliente(@RequestParam Integer idEndereco){
        return enderecoService.buscarEnderecoCliente(idEndereco);
    }

    @PutMapping("/alterar-endereco")
    public String alterarEndereco(@RequestParam Integer idEndereco, @RequestBody EnderecoDto enderecoDto){
        return enderecoService.alterarEndereco(idEndereco, enderecoDto);}

}
