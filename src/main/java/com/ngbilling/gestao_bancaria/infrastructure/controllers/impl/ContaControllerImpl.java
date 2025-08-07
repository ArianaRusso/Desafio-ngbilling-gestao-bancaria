package com.ngbilling.gestao_bancaria.infrastructure.controllers.impl;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.usecases.ConsultarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.CriarContaUseCase;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.ContaController;
import org.springframework.stereotype.Component;

@Component
public class ContaControllerImpl implements ContaController {

    private ConsultarContaUseCase consultarContaUseCase;

    private CriarContaUseCase criarContaUseCase;

    public ContaControllerImpl(ConsultarContaUseCase consultarContaUseCase, CriarContaUseCase criarContaUseCase) {
        this.consultarContaUseCase = consultarContaUseCase;
        this.criarContaUseCase = criarContaUseCase;
    }

    @Override
    public ContaBancaria consultar(Integer numeroConta) {
        return consultarContaUseCase.consultar(numeroConta);
    }

    @Override
    public ContaBancaria criarConta(ContaBancaria conta) {
        return criarContaUseCase.criarConta(conta);
    }
}
