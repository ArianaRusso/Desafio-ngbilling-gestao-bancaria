package com.ngbilling.gestao_bancaria.application.entities;

import com.ngbilling.gestao_bancaria.application.enums.FormaDePagamento;

import java.math.BigDecimal;

public class Transacao {

    private Integer numeroConta;

    private BigDecimal valor;

    private FormaDePagamento formaDePagamento;


    public Transacao(Integer numeroConta, BigDecimal valor, FormaDePagamento formaDePagamento) {
        this.numeroConta = numeroConta;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
    }

    public BigDecimal valorComTaxa() {
        return valor.add(valor.multiply(formaDePagamento.getTaxa()));
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }
}
