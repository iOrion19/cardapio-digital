package com.rfltech.cardapiodigital.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum MeioPagamentoEnum {

    ID_4("3913", "4"),
    ID_5("3913", "5"),
    ID_6("3911", "6"),
    ID_18("3913", "18"),
    ID_19("3913", "19"),
    ID_20("3912", "20"),
    ID_21("3913", "21"),
    ID_22("3913", "22"),
    ID_23("3913", "23"),
    ID_24("3912", "24"),
    ID_25("3913", "25"),
    ID_26("3913", "26"),
    ID_27("3913", "27"),
    ID_28("3912", "28"),
    ID_30("3913", "30"),
    ID_31("3912", "31"),
    ID_32("3912", "32"),
    ID_51("24560", "51");

    private final String codigoExterno;

    private final String idCardapio;

    MeioPagamentoEnum(String codigoExterno, String idCardapio) {
        this.codigoExterno = codigoExterno;
        this.idCardapio = idCardapio;
    }

    public static String obterCodigoCardapio(String idCardapio) {
        Stream<MeioPagamentoEnum> stream = Arrays.stream(MeioPagamentoEnum.values());

        return stream.filter(meioEnum -> meioEnum.getIdCardapio().contains(idCardapio))
                .findFirst()
                .map(MeioPagamentoEnum::getCodigoExterno)
                .orElse("");
    }

    public static boolean pagoOnline(String codigoPagamento) {
        return ID_51.getIdCardapio().equals(codigoPagamento);
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public String getIdCardapio() {
        return idCardapio;
    }
}