package com.ngbilling.gestao_bancaria.core.usecases;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;

import java.math.BigDecimal;

public interface ProcessarTransacaoUseCase {
    ContaBancaria processarPagamento(Integer numeroConta, BigDecimal valor, FormaDePagamento formaPagamento);
}
