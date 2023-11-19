package com.rfltech.cardapiodigital.model.enumerator;

public enum StatusEnum {

    PENDENTE(1),
    CONFIRMADO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(0);

    private Integer idCardapio;

    private StatusEnum(Integer idCardapio) {
        this.idCardapio = idCardapio;
    }

    public Integer getIdCardapio() {
        return this.idCardapio;
    }

    public void setIdCardapio(Integer idCardapio) {
        this.idCardapio = idCardapio;
    }

    public static String getNomePeloCodigo(Integer codigo) {
        StatusEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            StatusEnum status = var1[var3];
            if (status.idCardapio.equals(codigo)) {
                return status.name();
            }
        }

        return null;
    }

    public static Integer getCodigoPeloNome(String nome) {
        StatusEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            StatusEnum status = var1[var3];
            if (status.name().equalsIgnoreCase(nome)) {
                return status.getIdCardapio();
            }
        }

        return null;
    }
}
