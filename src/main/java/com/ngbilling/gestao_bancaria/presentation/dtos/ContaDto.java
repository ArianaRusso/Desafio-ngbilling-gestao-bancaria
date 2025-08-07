package com.ngbilling.gestao_bancaria.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ContaDto(
        @JsonProperty("numero_conta")
        Integer numeroConta,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal saldo
) {
}
