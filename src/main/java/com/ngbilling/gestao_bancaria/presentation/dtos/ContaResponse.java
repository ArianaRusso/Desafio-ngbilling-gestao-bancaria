package com.ngbilling.gestao_bancaria.presentation.dtos;

import java.math.BigDecimal;

public record ContaResponse(
        Integer numeroConta,
        BigDecimal saldo
) {
}
