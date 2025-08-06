package com.ngbilling.gestao_bancaria.application.enums;

import java.math.BigDecimal;

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

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

}
