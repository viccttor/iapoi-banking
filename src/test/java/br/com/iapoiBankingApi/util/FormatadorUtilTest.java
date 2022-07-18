package br.com.iapoiBankingApi.util;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FormatadorUtilTest {
    @Order(0)
    @Test
    void campoNulo(){
        String recebido = FormatadorUtil.removerCaractersEspeciais(null);
        String resultado = "Campo Nulo!";
        Assertions.assertEquals(resultado,recebido);
    }
    @Order(1)
    @Test
    void removerCaractersCpfNomalVerdade(){
        String recebido = FormatadorUtil.removerCaractersEspeciais("286.255.878-87");
        String resultado = "28625587887";
        Assertions.assertEquals(resultado,recebido);
    }
    @Order(2)
    @Test
    void removerCaractersCpfFaltandolVerdade(){
        String recebido = FormatadorUtil.removerCaractersEspeciais("286.25.87-87");
        String resultado = "286258787";
        Assertions.assertEquals(resultado,recebido);
    }
    @Order(3)
    @Test
    void removerCaractersvazioVerdade(){
        String recebido = FormatadorUtil.removerCaractersEspeciais(" ");
        String resultado = "";
        Assertions.assertEquals(resultado,recebido);
    }
    @Order(4)
    @Test
    void removerCaractersEspeciaisVerdade(){
        String recebido = FormatadorUtil.removerCaractersEspeciais("!@#$%¨&teste%¨&*()__-==++/*-+,,;/?|");
        String resultado = "teste";
        Assertions.assertEquals(resultado,recebido);
    }
    @Order(5)
    @Test
    void removerCaractersEspeciaisAcentoVerdade(){
        String recebido = FormatadorUtil.removerCaractersEspeciais("ÁÀtesteççâã");
        String resultado = "teste";
        Assertions.assertEquals(resultado,recebido);
    }
}
