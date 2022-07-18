package br.com.iapoiBankingApi.controller;

import br.com.iapoiBankingApi.dto.conta.MovimentacaoDto;
import br.com.iapoiBankingApi.dto.conta.MovimentacaoEntreContasDto;
import br.com.iapoiBankingApi.model.conta.Movimentacao;
import br.com.iapoiBankingApi.service.conta.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/listar-todas-movimentacoes")
    public List<Movimentacao> listarMovimentacao(){
        return movimentacaoService.listarMovimentacao();
    }

    @GetMapping("/movimentacao-por-conta")
    public List<Movimentacao> listarMovimentacaoPorConta(@RequestParam Integer idConta){
        return movimentacaoService.listarMovimentacaoPorConta(idConta);
    }

    @PostMapping("/nova-movimentacao")
    public String novaMovimentacao(@RequestBody MovimentacaoDto movimentacaoDto){
        return movimentacaoService.novaMovimentacao(movimentacaoDto);
    }

    @PostMapping("/entre-contas")
    public String gerarMovimentacao(@RequestBody MovimentacaoEntreContasDto movimentacaoEntreContasDto) {
        return movimentacaoService.NovaMovimentacaoEntreContas(movimentacaoEntreContasDto);
    }

    @PostMapping("/saque")
    public String saque(@RequestParam Integer idConta,@RequestParam Double valorSaque) {
        return movimentacaoService.saque(idConta,valorSaque);
    }

    @PostMapping("/resgate-poupanca")
    public String resgatePoupanca(@RequestParam Integer idConta){
        return movimentacaoService.resgatePoupanca(idConta);
    }


}
