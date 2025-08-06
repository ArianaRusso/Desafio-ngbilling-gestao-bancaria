package com.ngbilling.gestao_bancaria.infrastructure.repository;

import com.ngbilling.gestao_bancaria.infrastructure.persistence.ContaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaBancariaEntity, Long> {

    Optional<ContaBancariaEntity> findByNumeroConta(Integer numero);
}
