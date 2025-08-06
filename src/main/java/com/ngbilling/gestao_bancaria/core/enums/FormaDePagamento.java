package com.ngbilling.gestao_bancaria.core.enums;

import java.math.BigDecimal;
import java.util.Arrays;

public enum FormaDePagamento {

    P("Pix", new BigDecimal("0.00")),
    D("Cartão de Débito", new BigDecimal("0.03")),
    C("Cartão de Crédito", new BigDecimal("0.05"));

    private final String descricao;
    private final BigDecimal taxa;

    FormaDePagamento(String descricao, BigDecimal taxa) {
        this.descricao = descricao;
        this.taxa = taxa;
    }

    public static FormaDePagamento fromCodigo(String codigo) {
        return Arrays.stream(FormaDePagamento.values())
                .filter(fp -> fp.name().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Forma de pagamento inválida: " + codigo));
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

}
