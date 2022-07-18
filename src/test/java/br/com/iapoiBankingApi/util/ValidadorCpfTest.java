package br.com.iapoiBankingApi.util;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidadorCpfTest {

    @Order(0)
    @Test
    void nulo(){
        Boolean cpfValidado = ValidadorUtil.validarCpf(null);
        Assertions.assertFalse(cpfValidado);
    }
    @Order(1)
    @Test
    void MenorQue11False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("000.000-00");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(2)
    @Test
    void MaiorQue11False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("132321.231312 000.000-00");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(3)
    @Test
    void SequenciaDe0False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("000.000.000-00");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(4)
    @Test
    void SequenciaDe1False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("111.111.111-11");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(5)
    @Test
    void SequenciaDe2False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("222.222.222-22");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(6)
    @Test
    void SequenciaDe3False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("333.333.333-33");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(7)
    @Test
    void SequenciaDe4False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("444.444.444-44");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(8)
    @Test
    void SequenciaDe5False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("555.555.555-55");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(9)
    @Test
    void SequenciaDe6False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("666.666.666-66");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(10)
    @Test
    void SequenciaDe7False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("777.777.777-77");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(11)
    @Test
    void SequenciaDe8False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("888.888.888-88");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(12)
    @Test
    void SequenciaDe9False(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("999.999.999-99");
        Assertions.assertFalse(cpfValidado);
    }
    @Order(13)
    @Test
    void ResultadoTrue0(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("286.255.878-87");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(14)
    @Test
    void ResultadoTrue01(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("602.299.760-77");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(15)
    @Test
    void ResultadoTrue02(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("938.840.820-94");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(16)
    @Test
    void ResultadoTrue03(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("377.323.060-58");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(17)
    @Test
    void ResultadoTrue04(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("555.924.730-87");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(18)
    @Test
    void ResultadoTrue05(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("371.184.770-60");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(19)
    @Test
    void ResultadoTrue06(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("205.373.770-00");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(20)
    @Test
    void ResultadoTrue07(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("462.391.220-50");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(12)
    @Test
    void ResultadoTrue08(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("256.468.420-34");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(22)
    @Test
    void ResultadoTrue09(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("629.673.490-54");
        Assertions.assertTrue(cpfValidado);
    }
    @Order(23)
    @Test
    void ResultadoTrue10(){
        Boolean cpfValidado = ValidadorUtil.validarCpf("672.409.040-75");
        Assertions.assertTrue(cpfValidado);
    }

}
