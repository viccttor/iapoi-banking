package br.com.iapoiBankingApi.service;

import br.com.iapoiBankingApi.dto.cliente.ClienteDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.web.bind.annotation.RequestBody;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroDeClienteTest {

    @Order(0)
    @Test
    void CadastroNulo(@RequestBody ClienteDto clienteDto) {

    }
}
