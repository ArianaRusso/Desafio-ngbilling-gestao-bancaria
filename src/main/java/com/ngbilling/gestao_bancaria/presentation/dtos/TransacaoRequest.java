package com.ngbilling.gestao_bancaria.presentation.dtos;

import java.math.BigDecimal;

public record TransacaoRequest(
        String tipoTransacao,
        Integer numeroConta,
        BigDecimal valor
) {
}
