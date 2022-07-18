package br.com.iapoiBankingApi.util;

public class ValidadorUtil {

    public static Boolean validarCpf(String validarCpf){
        if (validarCpf == null) return false;
        String cpfNumero = FormatadorUtil.removerCaractersEspeciais(validarCpf);
        if (cpfNumero.length() != 11 || cpfNumero.equalsIgnoreCase("00000000000") ||
                cpfNumero.equalsIgnoreCase("11111111111") ||
                cpfNumero.equalsIgnoreCase("22222222222") ||
                cpfNumero.equalsIgnoreCase("33333333333") ||
                cpfNumero.equalsIgnoreCase("44444444444") ||
                cpfNumero.equalsIgnoreCase("55555555555") ||
                cpfNumero.equalsIgnoreCase("66666666666") ||
                cpfNumero.equalsIgnoreCase("77777777777") ||
                cpfNumero.equalsIgnoreCase("88888888888") ||
                cpfNumero.equalsIgnoreCase("99999999999"))
            return false;

        Integer somaDig9 = 0,somaDig10 = 0, condicao, dig9, dig10;

        condicao = 10;
        for (int i=0; i<9; i ++){
            Integer num = Integer.parseInt(String.valueOf(cpfNumero.charAt(i)));
            somaDig9 = somaDig9 + (num * condicao);
            condicao = condicao -1;
        }
        dig9 = somaDig9 * 10 % 11;
        if (dig9 == 10) {  dig9 = 0; }

        condicao = 11;
        for (int i=0; i<10; i ++){
            Integer num = Integer.parseInt(String.valueOf(cpfNumero.charAt(i)));
            somaDig10 = somaDig10 + (num * condicao);
            condicao = condicao -1;
        }
        dig10 = somaDig10 * 10 % 11;
        if (dig10 == 10) { dig10 = 0; }

        if(dig9.equals(cpfNumero.charAt(9)-48) || dig10.equals(cpfNumero.charAt(10)-48)) {
            return true;
        }else {
            return false;
        }
    }

    public static Boolean validarTamanhoString(Integer tamanho, String string){
        Integer tamanhoString = string.length();
        return tamanho <= tamanhoString;
    }
}
