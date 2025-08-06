package com.ngbilling.gestao_bancaria.infrastructure.gateways;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.gateways.ContaGateway;
import com.ngbilling.gestao_bancaria.infrastructure.mappers.ContaMapper;
import com.ngbilling.gestao_bancaria.infrastructure.persistence.ContaBancariaEntity;
import com.ngbilling.gestao_bancaria.infrastructure.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContaGatewayImpl implements ContaGateway {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaMapper mapper;

    @Override
    public Optional<ContaBancaria> findByNumero(Integer numero) {
        return repository.findByNumeroConta(numero).map(mapper::toDomain);
    }

    @Override
    public Optional<ContaBancaria> save(ContaBancaria conta) {
        ContaBancariaEntity savedEntity = repository.save(new ContaBancariaEntity(conta));
        return Optional.of(mapper.toDomain(savedEntity));
    }
}
