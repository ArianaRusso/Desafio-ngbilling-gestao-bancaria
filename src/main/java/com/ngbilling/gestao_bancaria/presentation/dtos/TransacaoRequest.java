package com.ngbilling.gestao_bancaria.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransacaoRequest(
        @JsonProperty("forma_pagamento")
        String tipoTransacao,
        @JsonProperty("numero_conta")
        Integer numeroConta,
        BigDecimal valor
) {
}
