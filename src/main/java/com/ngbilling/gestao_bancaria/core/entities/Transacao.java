package com.ngbilling.gestao_bancaria.core.entities;

import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;

import java.math.BigDecimal;

public class Transacao {

    private Integer numero_conta;

    private BigDecimal valor;

    private FormaDePagamento formaDePagamento;


    public Transacao(Integer numero_conta, BigDecimal valor, FormaDePagamento formaDePagamento) {
        this.numero_conta = numero_conta;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
    }

    public BigDecimal valorComTaxa() {
        return valor.add(valor.multiply(formaDePagamento.getTaxa()));
    }

    public Integer getNumero_conta() {
        return numero_conta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }
}
