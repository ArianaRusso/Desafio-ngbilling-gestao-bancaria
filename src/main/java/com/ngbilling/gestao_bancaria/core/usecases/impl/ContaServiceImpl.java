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

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class ContaServiceImpl implements ConsultarContaUseCase, CriarContaUseCase, ProcessarTransacaoUseCase {

    private final ContaGateway gateway;

    private static final AtomicInteger SEQUENCE = new AtomicInteger(1);

    public ContaServiceImpl(ContaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ContaBancaria consultar(Integer numeroConta) {
        return gateway.findByNumero(numeroConta).
                orElseThrow(() -> new ContaNaoEncontradaException("Conta numero " + numeroConta + "não encontrada"));
    }

    @Override
    public ContaBancaria criarConta() {

        ContaBancaria novaContaBancaria = new ContaBancaria(SEQUENCE.getAndIncrement());

        if (gateway.findByNumero(novaContaBancaria.getNumeroConta()).isPresent()) {
            throw new ContaJaExisteException("Conta " + novaContaBancaria + "já existe");
        }
        return gateway.save(novaContaBancaria)
                .orElseThrow(() -> new RuntimeException("Erro ao salvar a conta " + novaContaBancaria));
    }

    @Override
    public ContaBancaria processarPagamento(Integer numeroConta, BigDecimal valor, FormaDePagamento formaPagamento) {
        ContaBancaria conta = gateway.findByNumero(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta numero " + numeroConta + "não encontrada"));

        BigDecimal taxa = formaPagamento.getTaxa();
        BigDecimal valorComTaxa = valor.add(valor.multiply(taxa));

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo da conta numero " + numeroConta + "é insuficiente para realizar transação" );
        }

        conta.debitar(valorComTaxa);

        gateway.save(conta);
        return conta;
    }
}
