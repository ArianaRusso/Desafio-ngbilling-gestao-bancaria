package com.ngbilling.gestao_bancaria.infrastructure.mappers;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.infrastructure.persistence.ContaBancariaEntity;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    ContaBancaria entityToConta(ContaBancariaEntity entity);
    ContaBancaria dtoToConta (ContaDto dto);
}
