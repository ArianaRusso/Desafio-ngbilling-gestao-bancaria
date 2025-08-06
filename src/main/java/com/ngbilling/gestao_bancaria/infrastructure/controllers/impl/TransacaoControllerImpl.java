package com.ngbilling.gestao_bancaria.infrastructure.controllers.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.core.usecases.ProcessarTransacaoUseCase;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.TransacaoController;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransacaoControllerImpl implements TransacaoController {

    private ProcessarTransacaoUseCase processarTransacaoUseCase;

    @Override
    public ContaBancaria processarPagamento(Integer numeroConta, BigDecimal valor, FormaDePagamento formaPagamento) {
        return processarTransacaoUseCase.processarPagamento(numeroConta, valor, formaPagamento);
    }
}
