package com.ngbilling.gestao_bancaria.presentation.api;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.TransacaoController;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaDto;
import com.ngbilling.gestao_bancaria.presentation.dtos.TransacaoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoApiControllerTest {

    @Mock
    private TransacaoController controller;

    @InjectMocks
    private TransacaoApiController api;

    @Test
    void deveCriarTransacaoComSucesso() {
        TransacaoRequest request = new TransacaoRequest("D", 123, new BigDecimal("100.00"));
        FormaDePagamento forma = FormaDePagamento.D;

        ContaBancaria contaProcessada = new ContaBancaria(null, 123, new BigDecimal("97.00"));

        when(controller.processarPagamento(123, new BigDecimal("100.00"), forma))
                .thenReturn(contaProcessada);

        ResponseEntity<ContaDto> response = api.criarTransacao(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(123, response.getBody().numeroConta());
        assertEquals(new BigDecimal("97.00"), response.getBody().saldo());
    }

}