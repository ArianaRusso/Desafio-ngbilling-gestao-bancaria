package com.ngbilling.gestao_bancaria.core.gateways;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;

import java.util.Optional;

public interface ContaGateway {
    Optional<ContaBancaria> findByNumero(Integer numero);
    ContaBancaria save(ContaBancaria conta);

}
