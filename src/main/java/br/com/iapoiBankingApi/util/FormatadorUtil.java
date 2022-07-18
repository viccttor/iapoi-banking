package br.com.iapoiBankingApi.util;

public class FormatadorUtil {
    public static String removerCaractersEspeciais(String string) {
        if (string != null){
        return string.replaceAll("[^a-zA-Z0-9]", "");
        } else {
            return "Campo Nulo!";
        }

    }
}
