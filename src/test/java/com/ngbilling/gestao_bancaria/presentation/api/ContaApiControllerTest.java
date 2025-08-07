package com.ngbilling.gestao_bancaria.presentation.api;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.ContaController;
import com.ngbilling.gestao_bancaria.infrastructure.mappers.ContaMapper;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaDto;
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
class ContaApiControllerTest {

    @Mock
    private ContaController controller;

    @Mock
    private ContaMapper mapper;

    @InjectMocks
    private ContaApiController api;

    @Test
    void deveCriarContaComSucesso() {
        ContaDto inputDto = new ContaDto(123, new BigDecimal("0.00"));
        ContaBancaria conta = new ContaBancaria(null, 123, new BigDecimal("0.00"));

        when(mapper.dtoToConta(inputDto)).thenReturn(conta);
        when(controller.criarConta(conta)).thenReturn(conta);

        ResponseEntity<ContaDto> response = api.criarConta(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(123, response.getBody().numeroConta());
        assertEquals(new BigDecimal("0.00"), response.getBody().saldo());
    }

    @Test
    void deveConsultarContaExistente() {
        Integer numeroConta = 456;
        ContaBancaria conta = new ContaBancaria(null, numeroConta, new BigDecimal("150.00"));
        ContaDto esperado = new ContaDto(numeroConta, new BigDecimal("150.00"));

        when(controller.consultar(numeroConta)).thenReturn(conta);

        ResponseEntity<ContaDto> response = api.consultar(numeroConta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(esperado.numeroConta(), response.getBody().numeroConta());
        assertEquals(esperado.saldo(), response.getBody().saldo());
    }

    @Test
    void deveRetornar404_ContaNaoEncontrada() {
        Integer numeroConta = 999;

        when(controller.consultar(numeroConta)).thenReturn(null);

        ResponseEntity<ContaDto> response = api.consultar(numeroConta);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}