package br.com.iapoiBankingApi.repository;

import br.com.iapoiBankingApi.model.conta.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findByIdConta(Integer idConta);
}
