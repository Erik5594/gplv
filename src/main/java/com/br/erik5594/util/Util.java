package com.br.erik5594.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String formatarNumeroTelefone(String numeroTelefone){
        if(StringUtils.isNotBlank(numeroTelefone)){
            if(numeroTelefone.length() >= 4){
                if(numeroTelefone.length() == 13) {
                    numeroTelefone = "+" + numeroTelefone.substring(0, 2) + " (" + numeroTelefone.substring(2, 4) + ") " + numeroTelefone.substring(4, 9) + "-" + numeroTelefone.substring(9);
                }else if(numeroTelefone.length() == 12){
                    numeroTelefone = "+"+numeroTelefone.substring(0, 2)+" (" + numeroTelefone.substring(2, 4) + ") " + numeroTelefone.substring(4,8)+"-"+numeroTelefone.substring(8);
                }else if(numeroTelefone.length() == 11) {
                    numeroTelefone = "(" + numeroTelefone.substring(0, 2) + ") " + numeroTelefone.substring(2,7)+"-"+numeroTelefone.substring(7);
                }else if (numeroTelefone.length() == 10){
                    numeroTelefone = "(" + numeroTelefone.substring(0, 2) + ") " + numeroTelefone.substring(2,6)+"-"+numeroTelefone.substring(6);
                }else if (numeroTelefone.length() == 9){
                    numeroTelefone = numeroTelefone.substring(0,5)+"-"+numeroTelefone.substring(5);
                }else if (numeroTelefone.length() < 9){
                    numeroTelefone = numeroTelefone.substring(0,4)+"-"+numeroTelefone.substring(4);
                }
            }
        }
        return numeroTelefone;
    }

    public static String formataCpfCnpj(String cpfCnpj){
        if(StringUtils.isNotBlank(cpfCnpj)){
            if(cpfCnpj.length() == 14){
                cpfCnpj = cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2,5) + "." + cpfCnpj.substring(5,8) + "/" + cpfCnpj.substring(8,12)+ "-" + cpfCnpj.substring(12);
            }else if(cpfCnpj.length() == 11) {
                cpfCnpj = cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3,6) + "." + cpfCnpj.substring(6,9) + "-" + cpfCnpj.substring(9);
            }
        }
        return cpfCnpj;
    }

    public static String formatarData(Date data, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(data);
    }

    public static Date formatarData(String data, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(data);
    }
}
