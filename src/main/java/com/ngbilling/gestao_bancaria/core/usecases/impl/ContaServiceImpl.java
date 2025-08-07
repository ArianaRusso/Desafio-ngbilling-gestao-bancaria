package com.ngbilling.gestao_bancaria.core.usecases.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.core.exceptions.ContaJaExisteException;
import com.ngbilling.gestao_bancaria.core.exceptions.ContaNaoEncontradaException;
import com.ngbilling.gestao_bancaria.core.exceptions.SaldoInsuficienteException;
import com.ngbilling.gestao_bancaria.core.gateways.ContaGateway;
import com.ngbilling.gestao_bancaria.core.usecases.ConsultarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.CriarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.ProcessarTransacaoUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaServiceImpl implements ConsultarContaUseCase, CriarContaUseCase, ProcessarTransacaoUseCase {

    private final ContaGateway gateway;

    public ContaServiceImpl(ContaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ContaBancaria consultar(Integer numeroConta) {
        return gateway.findByNumero(numeroConta).
                orElseThrow(() -> new ContaNaoEncontradaException("Conta numero " + numeroConta + "não encontrada"));
    }

    @Transactional
    @Override
    public ContaBancaria criarConta(ContaBancaria conta) {

        if (gateway.findByNumero(conta.getNumeroConta()).isPresent()) {
            throw new ContaJaExisteException("Conta " + conta + "já existe");
        }
        return gateway.save(conta)
                .orElseThrow(() -> new RuntimeException("Erro ao cadastrar conta " + conta));
    }

    @Override
    public ContaBancaria processarPagamento(Integer numeroConta, BigDecimal valor, FormaDePagamento formaPagamento) {
        ContaBancaria conta = gateway.findByNumero(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta numero " + numeroConta + "não encontrada"));

        BigDecimal taxa = formaPagamento.getTaxa();
        BigDecimal valorComTaxa = valor
                .add(valor.multiply(taxa))
                .setScale(2, RoundingMode.HALF_UP);

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo da conta numero " + numeroConta + "é insuficiente para realizar transação" );
        }

        conta.debitar(valorComTaxa);

        gateway.save(conta);
        return conta;
    }
}
