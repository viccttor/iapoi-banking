package br.com.iapoiBankingApi.repository;

import br.com.iapoiBankingApi.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByCpf(String cpf);
    Optional<Cliente> findById(Integer id);


}
