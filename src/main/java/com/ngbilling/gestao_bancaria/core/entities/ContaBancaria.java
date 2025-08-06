package com.ngbilling.gestao_bancaria.core.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ContaBancaria {

    private Long id;
    private Integer numeroConta;
    private BigDecimal saldo;

    public ContaBancaria(Integer numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = new BigDecimal(BigInteger.ZERO);
    }

    public Long getId() {
        return id;
    }
    public Integer getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void debitar(BigDecimal valorComTaxa) {
        saldo = saldo.subtract(valorComTaxa);
    }
}
