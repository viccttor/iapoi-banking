package br.com.iapoiBankingApi.repository;

import br.com.iapoiBankingApi.model.cliente.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
