package com.ngbilling.gestao_bancaria.infrastructure.controllers;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;

import java.util.Optional;

public interface ContaController {
    ContaBancaria consultar(Integer numeroConta);
    ContaBancaria criarConta (ContaBancaria conta);

}
