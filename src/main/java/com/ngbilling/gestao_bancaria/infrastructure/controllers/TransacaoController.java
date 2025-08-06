package com.ngbilling.gestao_bancaria.infrastructure.controllers;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;

import java.math.BigDecimal;

public interface TransacaoController {
    ContaBancaria processarPagamento(Integer numeroConta, BigDecimal valor, FormaDePagamento formaPagamento);
}
