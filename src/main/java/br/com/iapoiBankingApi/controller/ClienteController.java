package br.com.iapoiBankingApi.controller;

import br.com.iapoiBankingApi.dto.cliente.ClienteDto;
import br.com.iapoiBankingApi.model.cliente.Cliente;
import br.com.iapoiBankingApi.service.cliente.ClienteService;
import br.com.iapoiBankingApi.service.conta.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar-todos-clientes")
    public List<Cliente> listarCliente(){
        return clienteService.listarClientes();
    }

    @PostMapping("/cadastrar-cliente")
    public String cadastrarCliente(@RequestBody ClienteDto clienteDto){
        return clienteService.cadastrarCliente(clienteDto);
    }

    @GetMapping("/buscar-saldo")
    public String saldoCliente(@RequestParam String cliente_id){
        return clienteService.buscarSaldoCliente(cliente_id);
    }

    @GetMapping("/buscar-cliente")
    public List<Cliente> buscarCliente(@RequestParam String cpf){
        return clienteService.buscarCliente(cpf);
    }

    @DeleteMapping("/deletar-cliente")
    public String deletarCliente(@RequestParam Integer id) {
        return clienteService.deletarCliente(id);
    }

    @PutMapping("/ativar-conta")
    public String ativarConta(@RequestParam Integer idConta){
        return clienteService.ativarConta(idConta);
    }

    @PutMapping("/inativar-conta")
    public String inativarConta(@RequestParam Integer idConta){
        return clienteService.inativarConta(idConta);
    }
}
