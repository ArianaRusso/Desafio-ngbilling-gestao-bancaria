package com.ngbilling.gestao_bancaria.infrastructure.controllers.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.usecases.ConsultarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.CriarContaUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ContaControllerImplTest {

    @Mock
    private ConsultarContaUseCase consultarContaUseCase;

    @Mock
    private CriarContaUseCase criarContaUseCase;

    @InjectMocks
    private ContaControllerImpl controller;

    @Test
    void deveConsultarConta() {
        ContaBancaria conta = new ContaBancaria(null, 123, new BigDecimal("100.00"));
        when(consultarContaUseCase.consultar(123)).thenReturn(conta);

        ContaBancaria resultado = controller.consultar(123);

        assertEquals(123, resultado.getNumeroConta());
        assertEquals(new BigDecimal("100.00"), resultado.getSaldo());
    }

    @Test
    void deveCriarConta() {
        ContaBancaria novaConta = new ContaBancaria(null, 456, BigDecimal.ZERO);
        when(criarContaUseCase.criarConta(novaConta)).thenReturn(novaConta);

        ContaBancaria resultado = controller.criarConta(novaConta);

        assertEquals(456, resultado.getNumeroConta());
        assertEquals(BigDecimal.ZERO, resultado.getSaldo());
    }

}