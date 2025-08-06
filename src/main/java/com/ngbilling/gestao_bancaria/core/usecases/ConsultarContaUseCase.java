package com.ngbilling.gestao_bancaria.core.usecases;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;

import java.util.Optional;

public interface ConsultarContaUseCase {

    Optional<ContaBancaria> consultar(Integer numeroConta);

}
