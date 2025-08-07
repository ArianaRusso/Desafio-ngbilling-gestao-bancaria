package com.ngbilling.gestao_bancaria.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ContaDto(
        @JsonProperty("numero_conta")
        Integer numeroConta,
        BigDecimal saldo
) {
}
