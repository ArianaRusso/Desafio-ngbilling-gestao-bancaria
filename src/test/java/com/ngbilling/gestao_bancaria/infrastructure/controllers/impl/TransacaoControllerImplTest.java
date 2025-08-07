package com.ngbilling.gestao_bancaria.infrastructure.controllers.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.core.usecases.ProcessarTransacaoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransacaoControllerImplTest {

    @Mock
    private ProcessarTransacaoUseCase processarTransacaoUseCase;

    @InjectMocks
    private TransacaoControllerImpl controller;

    @Test
    void deveProcessarPagamento() {
        Integer numeroConta = 789;
        BigDecimal valor = new BigDecimal("100.00");
        FormaDePagamento forma = FormaDePagamento.D;

        ContaBancaria contaComSaldoAtualizado = new ContaBancaria(null, numeroConta, new BigDecimal("97.00"));
        when(processarTransacaoUseCase.processarPagamento(numeroConta, valor, forma))
                .thenReturn(contaComSaldoAtualizado);

        ContaBancaria resultado = controller.processarPagamento(numeroConta, valor, forma);

        assertEquals(numeroConta, resultado.getNumeroConta());
        assertEquals(new BigDecimal("97.00"), resultado.getSaldo());
    }
}