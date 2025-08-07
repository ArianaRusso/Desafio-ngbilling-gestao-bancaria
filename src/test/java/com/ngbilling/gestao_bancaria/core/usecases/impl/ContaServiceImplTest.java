package com.ngbilling.gestao_bancaria.core.usecases.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.core.exceptions.ContaJaExisteException;
import com.ngbilling.gestao_bancaria.core.exceptions.ContaNaoEncontradaException;
import com.ngbilling.gestao_bancaria.core.exceptions.SaldoInsuficienteException;
import com.ngbilling.gestao_bancaria.core.gateways.ContaGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    @Mock
    private ContaGateway gateway;

    @InjectMocks
    private ContaServiceImpl service;

    @Test
    void deveConsultarContaExistente() {
        ContaBancaria conta = new ContaBancaria(null, 123, new BigDecimal("100.00"));
        when(gateway.findByNumero(123)).thenReturn(Optional.of(conta));

        ContaBancaria resultado = service.consultar(123);

        assertEquals(123, resultado.getNumeroConta());
        assertEquals(new BigDecimal("100.00"), resultado.getSaldo());
    }

    @Test
    void deveLancarExcecao_ContaNaoEncontrada() {
        when(gateway.findByNumero(123)).thenReturn(Optional.empty());

        assertThrows(ContaNaoEncontradaException.class, () -> service.consultar(123));
    }

    @Test
    void deveCriarContaNova() {
        ContaBancaria novaConta = new ContaBancaria(null, 456, new BigDecimal("0.00"));
        when(gateway.findByNumero(456)).thenReturn(Optional.empty());
        when(gateway.save(novaConta)).thenReturn(Optional.of(novaConta));

        ContaBancaria criada = service.criarConta(novaConta);

        assertEquals(456, criada.getNumeroConta());
    }

    @Test
    void deveLancarExcecao_ContaJaExiste() {
        ContaBancaria contaExistente = new ContaBancaria(null, 456, new BigDecimal("50.00"));
        when(gateway.findByNumero(456)).thenReturn(Optional.of(contaExistente));

        ContaBancaria tentativa = new ContaBancaria(null, 456, BigDecimal.ZERO);

        assertThrows(ContaJaExisteException.class, () -> service.criarConta(tentativa));
    }

    @Test
    void deveProcessarPagamentoComSaldoSuficiente() {
        ContaBancaria conta = new ContaBancaria(null, 789, new BigDecimal("200.00"));
        when(gateway.findByNumero(789)).thenReturn(Optional.of(conta));
        when(gateway.save(any())).thenReturn(Optional.of(conta));

        ContaBancaria resultado = service.processarPagamento(789, new BigDecimal("100.00"), FormaDePagamento.D);

        assertEquals(new BigDecimal("97.00"), resultado.getSaldo());
    }

    @Test
    void deveLancarExcecao_SaldoInsuficiente() {
        ContaBancaria conta = new ContaBancaria(null, 321, new BigDecimal("50.00"));
        when(gateway.findByNumero(321)).thenReturn(Optional.of(conta));

        assertThrows(SaldoInsuficienteException.class,
                () -> service.processarPagamento(321, new BigDecimal("100.00"), FormaDePagamento.D));
    }
}